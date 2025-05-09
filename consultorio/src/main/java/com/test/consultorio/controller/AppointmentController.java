package com.test.consultorio.controller;

import com.test.consultorio.entity.Appointment;
import com.test.consultorio.model.Request.AppointmentRequest;
import com.test.consultorio.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private IAppointmentService iAppointmentService;

    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentRequest request) {
        String resultado = iAppointmentService.createAppointment(request); // Ajustar usuario
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<Appointment>> getAppointmentsByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(iAppointmentService.findAppointmentsByDateRange(start, end));
    }

    @GetMapping("/by-medical-office/{medicalOfficeId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByMedicalOffice(@PathVariable Integer medicalOfficeId) {
        return ResponseEntity.ok(iAppointmentService.findAppointmentsByMedicalOffice(medicalOfficeId));
    }

    @GetMapping("/by-doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable Integer doctorId) {
        return ResponseEntity.ok(iAppointmentService.findAppointmentsByDoctor(doctorId));
    }

    @GetMapping("/by-doctor/{doctorId}/date")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorAndDate(
            @PathVariable Integer doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(iAppointmentService.findAppointmentsByDoctorAndDate(doctorId, date));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Integer id) {
        iAppointmentService.cancelAppointment(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> editAppointment(
            @PathVariable Integer id,
            @RequestBody AppointmentRequest request) {
        Appointment appointment = iAppointmentService.editAppointment(id, request);
        return ResponseEntity.ok(appointment);
    }



}

