package com.test.consultorio.service;

import com.test.consultorio.entity.Doctor;
import com.test.consultorio.model.response.DoctorResponse;

import java.util.List;

public interface IDoctorService {
    public List<DoctorResponse> getDoctorList();
    public Doctor findDoctor(Integer id);

}
