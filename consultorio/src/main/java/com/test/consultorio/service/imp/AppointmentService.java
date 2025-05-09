package com.test.consultorio.service.imp;

import com.test.consultorio.entity.Appointment;
import com.test.consultorio.entity.Doctor;
import com.test.consultorio.entity.MedicalOffice;
import com.test.consultorio.entity.Patient;
import com.test.consultorio.exception.NotFoundException;
import com.test.consultorio.model.Request.AppointmentRequest;
import com.test.consultorio.repository.IAppointmentRepository;
import com.test.consultorio.service.IAppointmentService;
import com.test.consultorio.service.IDoctorService;
import com.test.consultorio.service.IMedicalOfficeService;
import com.test.consultorio.service.IPatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentService implements IAppointmentService {

    private final IAppointmentRepository iAppointmentRepository;
    private final IPatientService iPatientService;
    private final IDoctorService iDoctorService;
    private final IMedicalOfficeService iMedicalOfficeService;

    @Transactional
    public String createAppointment(AppointmentRequest request){

        Patient patient = iPatientService.findPatient(request.getPatientId());
        Doctor doctor = iDoctorService.findDoctor(request.getDoctorId());
        MedicalOffice medicalOffice = iMedicalOfficeService.findMedicalOffice(request.getMedicalOfficeId());
        LocalDateTime startTime = request.getAppointmentDateTime();
        LocalDateTime endTime = startTime.plusMinutes(request.getDurationMinutes());
        LocalDate date = startTime.toLocalDate();

        List<Appointment> consultarioOcupado = iAppointmentRepository.findOverlappingByMedicalOffice(
                medicalOffice.getId(), startTime, endTime);
        if (!consultarioOcupado.isEmpty()) {
            throw new IllegalArgumentException("EL CONSULTORIO SE ENCUENTRA OCUPADO EN ESE HORARIO");
        }

        List<Appointment> doctorOcupado = iAppointmentRepository.findOverlappingByDoctor(
                doctor.getId(), startTime, endTime);
        if (!doctorOcupado.isEmpty()) {
            throw new IllegalArgumentException("EL DOCTOR CUENTA CON UNA CITA EN ESE HORARIO");
        }

        List<Appointment> pacienteConCita = iAppointmentRepository.findOverlappingOrCloseByPatient(
                patient.getId(), startTime, endTime, date);
        if (!pacienteConCita.isEmpty()) {
            throw new IllegalArgumentException("EL PACIENTE YA TIENE CITA, O SU CITA SE ENCUENTRA A MENOS DE DOS HORAS DE OTRA CITA DEL PACIENTE");
        }

        Integer citasDelDoctor = iAppointmentRepository.countAppointmentsByDoctorAndDate(doctor.getId(), date);
        if (citasDelDoctor >= 8) {
            throw new IllegalArgumentException("EL DOCTOR YA CUENTA CON 8 CITAS ESTE DIA, NO SE PUEDE AGENDAR UNA MÁS");
        }

        Appointment cita = Appointment.builder()
                .patient(patient)
                .doctor(doctor)
                .medicalOffice(medicalOffice)
                .appointmentDate(startTime)
                .duration(request.getDurationMinutes())
                .status("PENDIENTE")
                .enabled(true)
                .creatorUser("SECRETARIO")
                .creationDate(LocalDateTime.now())
                .build();

        iAppointmentRepository.save(cita);
        return "CITA CREADA";
    }

    public List<Appointment> findAppointmentsByDateRange(LocalDateTime start, LocalDateTime end) {
        return iAppointmentRepository.findByAppointmentDateTimeBetweenAndEnabledTrue(start, end);
    }

    public List<Appointment> findAppointmentsByMedicalOffice(Integer medicalOfficeId) {
        MedicalOffice medicalOffice = iMedicalOfficeService.findMedicalOffice(medicalOfficeId);
        return iAppointmentRepository.findByMedicalOfficeAndEnabledTrue(medicalOffice);
    }

    public List<Appointment> findAppointmentsByDoctor(Integer doctorId) {
        Doctor doctor = iDoctorService.findDoctor(doctorId);
        return iAppointmentRepository.findByDoctorAndEnabledTrue(doctor);
    }

    public List<Appointment> findAppointmentsByDoctorAndDate(Integer doctorId, LocalDate date) {
        return iAppointmentRepository.findByDoctorIdAndDate(doctorId, date);
    }

    @Transactional
    public void cancelAppointment(Integer appointmentId) {
        Appointment appointment = iAppointmentRepository.findById(appointmentId)
                .orElseThrow(()->new NotFoundException("CITA NO ENCONTRADA"));

        if (!appointment.getStatus().equals("PENDIENTE")) {
            throw new IllegalArgumentException("SOLO SE PUEDEN CANCELAR CITAS PENDIENTE.");
        }

        if (appointment.getAppointmentDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("NO SE PUEDEN CANCELAR CITAS PASADAS");
        }

        appointment.setStatus("CANCELADA");
        appointment.setModificationDate(LocalDateTime.now());
        appointment.setModificationUser("SECRETARIO");
        iAppointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment editAppointment(Integer appointmentId, AppointmentRequest request, String modifierUser) {
        Appointment appointment = iAppointmentRepository.findById(appointmentId)
                .orElseThrow(()->new NotFoundException("CITA NO ENCONTRADA"));

        if (!appointment.getStatus().equals("PENDIENTE")) {
            throw new IllegalArgumentException("Solo se pueden editar citas en estado PENDIENTE.");
        }

        if (appointment.getAppointmentDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("No se pueden editar citas ya pasadas");
        }

        Patient patient = iPatientService.findPatient(request.getPatientId());
        Doctor doctor = iDoctorService.findDoctor(request.getDoctorId());
        MedicalOffice medicalOffice = iMedicalOfficeService.findMedicalOffice(request.getMedicalOfficeId());

        LocalDateTime startTime = request.getAppointmentDateTime();
        LocalDateTime endTime = startTime.plusMinutes(request.getDurationMinutes());
        LocalDate date = startTime.toLocalDate();

        List<Appointment> overlappingOffice = iAppointmentRepository.findOverlappingByMedicalOffice(
                request.getMedicalOfficeId(), startTime, endTime);
        overlappingOffice.removeIf(a -> a.getId().equals(appointmentId));
        if (!overlappingOffice.isEmpty()) {
            throw new IllegalArgumentException("EL CONSULTORIO SE ENCUENTRA OCUPADO EN ESE HORARIO");
        }

        List<Appointment> overlappingDoctor = iAppointmentRepository.findOverlappingByDoctor(
                request.getDoctorId(), startTime, endTime);
        overlappingDoctor.removeIf(a -> a.getId().equals(appointmentId));
        if (!overlappingDoctor.isEmpty()) {
            throw new IllegalArgumentException("EL DOCTOR CUENTA CON UNA CITA EN ESE HORARIO");
        }

        List<Appointment> overlappingPatient = iAppointmentRepository.findOverlappingOrCloseByPatient(
                request.getPatientId(), startTime, endTime, date);
        overlappingPatient.removeIf(a -> a.getId().equals(appointmentId));
        if (!overlappingPatient.isEmpty()) {
            throw new IllegalArgumentException("EL PACIENTE YA TIENE CITA, O SU CITA SE ENCUENTRA A MENOS DE DOS HORAS DE OTRA CITA DEL PACIENTE");
        }

        long appointmentCount = iAppointmentRepository.countAppointmentsByDoctorAndDate(request.getDoctorId(), date);
        if (appointmentCount > 8 || (appointmentCount == 8 && request.getDoctorId() != appointment.getDoctor().getId())) {
            throw new IllegalArgumentException("EL DOCTOR YA CUENTA CON 8 CITAS ESTE DIA, NO SE PUEDE AGENDAR UNA MÁS");
        }

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setMedicalOffice(medicalOffice);
        appointment.setAppointmentDate(startTime);
        appointment.setDuration(request.getDurationMinutes());
        appointment.setModificationDate(LocalDateTime.now());
        appointment.setModificationUser(modifierUser);

        return iAppointmentRepository.save(appointment);
    }

}
