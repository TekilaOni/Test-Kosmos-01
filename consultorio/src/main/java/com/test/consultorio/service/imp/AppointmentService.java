package com.test.consultorio.service.imp;

import com.test.consultorio.repository.IAppointmentRepository;
import com.test.consultorio.service.IAppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentService implements IAppointmentService {

    private final IAppointmentRepository iAppointmentRepository;

    public String createAppointment(){

    }

}
