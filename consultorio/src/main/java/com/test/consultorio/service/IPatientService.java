package com.test.consultorio.service;

import com.test.consultorio.model.response.PatientResponse;

import java.util.List;

public interface IPatientService {

    public List<PatientResponse> getPatientList();
}
