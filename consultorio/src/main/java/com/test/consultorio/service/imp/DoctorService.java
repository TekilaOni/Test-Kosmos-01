package com.test.consultorio.service.imp;

import com.test.consultorio.entity.Doctor;
import com.test.consultorio.model.response.DoctorResponse;
import com.test.consultorio.repository.IDoctorRepository;
import com.test.consultorio.service.IDoctorService;
import com.test.consultorio.service.ISpecialityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService implements IDoctorService {

    private final IDoctorRepository iDoctorRepository;

    public List<DoctorResponse> getDoctorList(){
        List<Doctor> listaDoctores = iDoctorRepository.findAll();
        List<DoctorResponse> response = new ArrayList<>();
        for(Doctor entry: listaDoctores){
            DoctorResponse doc = DoctorResponse.builder()
                    .id(entry.getId())
                    .name(entry.getName())
                    .lastName(entry.getLastName())
                    .secondLastName(entry.getSecondLastName())
                    .specialityName(entry.getSpeciality().getName())
                    .build();
            response.add(doc);
        }
        return response;
    }
}
