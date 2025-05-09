package com.test.consultorio.service.imp;

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

    }

}
