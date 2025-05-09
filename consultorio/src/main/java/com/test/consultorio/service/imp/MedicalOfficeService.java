package com.test.consultorio.service.imp;

import com.test.consultorio.entity.MedicalOffice;
import com.test.consultorio.model.response.MedicalOfficeResponse;
import com.test.consultorio.repository.IMedicalOfficeRepository;
import com.test.consultorio.service.IMedicalOfficeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MedicalOfficeService implements IMedicalOfficeService {

    private final IMedicalOfficeRepository iMedicalOfficeRepository;

    public List<MedicalOfficeResponse> getAllMedicalOffice(){
        List<MedicalOffice> allMedicalOffice = iMedicalOfficeRepository.findAll();
        List<MedicalOfficeResponse> response = new ArrayList<>();
        for(MedicalOffice med: allMedicalOffice){
            MedicalOfficeResponse res = MedicalOfficeResponse.builder()
                    .id(med.getId())
                    .number(med.getNumber())
                    .floor(med.getFloor())
                    .build();
            response.add(res);
        }
        return response;
    }
}
