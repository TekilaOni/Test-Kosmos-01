package com.test.consultorio.service;

import com.test.consultorio.model.response.MedicalOfficeResponse;

import java.util.List;

public interface IMedicalOfficeService {
    public List<MedicalOfficeResponse> getAllMedicalOffice();
}
