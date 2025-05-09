package com.test.consultorio.service;

import com.test.consultorio.entity.MedicalOffice;
import com.test.consultorio.model.response.MedicalOfficeResponse;

import java.util.List;

public interface IMedicalOfficeService {
    public List<MedicalOfficeResponse> getAllMedicalOffice();
    public MedicalOffice findMedicalOffice(Integer id);
}
