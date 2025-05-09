package com.test.consultorio.model;

import com.test.consultorio.entity.Doctor;
import com.test.consultorio.entity.MedicalOffice;
import com.test.consultorio.entity.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medical_office_id")
    private MedicalOffice medicalOffice;

    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;

    @Column(name = "enabled", nullable = false)
    @Builder.Default
    private Boolean enabled = true;

    @Column(name = "external_id", nullable = false)
    private Integer externalId;

    @Column(name = "creator_user", length = 30, nullable = false)
    private String creatorUser;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    @Column(name = "modification_user", length = 30)
    private String modificationUser;


}
