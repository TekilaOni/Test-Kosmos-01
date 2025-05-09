package com.test.consultorio.model.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppointmentRequest {
    private Integer patientId;
    private Integer doctorId;
    private Integer medicalOfficeId;
    private LocalDateTime appointmentDateTime;
    private int durationMinutes = 30; // Valor por defecto
}