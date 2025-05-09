package com.test.consultorio.service;

import com.test.consultorio.entity.Appointment;
import com.test.consultorio.model.Request.AppointmentRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {

    public String createAppointment(AppointmentRequest request);
    public List<Appointment> findAppointmentsByDateRange(LocalDateTime start, LocalDateTime end);
    public List<Appointment> findAppointmentsByMedicalOffice(Integer medicalOfficeId);
    public List<Appointment> findAppointmentsByDoctor(Integer doctorId);public List<Appointment> findAppointmentsByDoctorAndDate(Integer doctorId, LocalDate date);
    public void cancelAppointment(Integer appointmentId);
    public Appointment editAppointment(Integer appointmentId, AppointmentRequest request, String modifierUser);
}
