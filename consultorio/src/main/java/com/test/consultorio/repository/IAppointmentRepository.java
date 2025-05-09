package com.test.consultorio.repository;

import com.test.consultorio.entity.Appointment;
import com.test.consultorio.entity.Doctor;
import com.test.consultorio.entity.MedicalOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IAppointmentRepository extends PagingAndSortingRepository<Appointment, Integer>,
        JpaRepository<Appointment, Integer>,
        JpaSpecificationExecutor<Appointment> {

    @Query("SELECT a FROM Appointment a WHERE a.medicalOffice.id = :medicalOfficeId " +
            "AND a.enabled = true AND a.status NOT IN ('CANCELADA', 'COMPLETADA') " +
            "AND (a.appointmentDateTime < :endTime AND :startTime < a.appointmentDateTime + INTERVAL '1 SECOND' * a.durationMinutes * 60)")
    List<Appointment> findOverlappingByMedicalOffice(Integer medicalOfficeId, LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId " +
            "AND a.enabled = true AND a.status NOT IN ('CANCELADA', 'COMPLETADA') " +
            "AND (a.appointmentDateTime < :endTime AND :startTime < a.appointmentDateTime + INTERVAL '1 SECOND' * a.durationMinutes * 60)")
    List<Appointment> findOverlappingByDoctor(Integer doctorId, LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId " +
            "AND a.enabled = true AND a.status NOT IN ('CANCELADA', 'COMPLETADA') " +
            "AND DATE(a.appointmentDateTime) = :date " +
            "AND ((a.appointmentDateTime < :endTime AND :startTime < a.appointmentDateTime + INTERVAL '1 SECOND' * a.durationMinutes * 60) " +
            "OR (a.appointmentDateTime + INTERVAL '1 SECOND' * a.durationMinutes * 60 + INTERVAL '2 HOURS' > :startTime))")
    List<Appointment> findOverlappingOrCloseByPatient(Integer patientId, LocalDateTime startTime, LocalDateTime endTime, LocalDate date);

    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.doctor.id = :doctorId " +
            "AND a.enabled = true AND a.status NOT IN ('CANCELLED', 'COMPLETED') " +
            "AND DATE(a.appointmentDateTime) = :date")
    Integer countAppointmentsByDoctorAndDate(Integer doctorId, LocalDate date);

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId " +
            "AND a.enabled = true AND a.status NOT IN ('CANCELLED', 'COMPLETED') " +
            "AND DATE(a.appointmentDateTime) = :date")
    List<Appointment> findByDoctorIdAndDate(Integer doctorId, LocalDate date);

    List<Appointment> findByAppointmentDateTimeBetweenAndEnabledTrue(LocalDateTime start, LocalDateTime end);

    List<Appointment> findByMedicalOfficeAndEnabledTrue(MedicalOffice medicalOffice);

    List<Appointment> findByDoctorAndEnabledTrue(Doctor doctor);
    
}


