package com.test.consultorio.service.imp;

import com.test.consultorio.entity.Doctor;
import com.test.consultorio.entity.Patient;
import com.test.consultorio.exception.NotFoundException;
import com.test.consultorio.model.response.DoctorResponse;
import com.test.consultorio.model.response.PatientResponse;
import com.test.consultorio.repository.IPatientRepository;
import com.test.consultorio.service.IPatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PatientService implements IPatientService {

    private final IPatientRepository iPatientRepository;

    public List<PatientResponse> getPatientList(){
        List<Patient> listaPacientes = iPatientRepository.findAll();
        List<PatientResponse> response = new ArrayList<>();
        for(Patient entry: listaPacientes){
            PatientResponse patientResponse = PatientResponse.builder()
                    .id(entry.getId())
                    .name(entry.getName())
                    .lastName(entry.getLastName())
                    .secondLastName(entry.getSecondLastName())
                    .build();
            response.add(patientResponse);
        }
        return response;
    }

    public Patient findPatient(Integer id){
        Patient patient = iPatientRepository.findById(id).orElseThrow(()->new NotFoundException("PACIENTE NO ENCONTRADO"));
        return patient;
    }
}
