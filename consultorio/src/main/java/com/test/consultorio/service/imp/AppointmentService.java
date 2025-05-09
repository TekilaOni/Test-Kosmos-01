package com.test.consultorio.service.imp;

import com.test.consultorio.entity.Appointment;
import com.test.consultorio.entity.Doctor;
import com.test.consultorio.entity.MedicalOffice;
import com.test.consultorio.entity.Patient;
import com.test.consultorio.model.Request.AppointmentRequest;
import com.test.consultorio.repository.IAppointmentRepository;
import com.test.consultorio.service.IAppointmentService;
import com.test.consultorio.service.IDoctorService;
import com.test.consultorio.service.IMedicalOfficeService;
import com.test.consultorio.service.IPatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    }

}
