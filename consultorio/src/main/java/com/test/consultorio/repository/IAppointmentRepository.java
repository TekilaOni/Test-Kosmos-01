package com.test.consultorio.repository;

import com.test.consultorio.entity.Appointment;
import com.test.consultorio.entity.Doctor;
import com.test.consultorio.entity.MedicalOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IAppointmentRepository extends PagingAndSortingRepository<Appointment, Integer>,
        JpaRepository<Appointment, Integer>,
        JpaSpecificationExecutor<Appointment> {

    @Query(value = "SELECT * FROM appointment a WHERE a.medical_office_id = :medicalOfficeId " +
            "AND a.enabled = true AND a.status NOT IN ('CANCELADA', 'COMPLETADA') " +
            "AND (a.appointment_date < :endTime AND :startTime < a.appointment_date + INTERVAL 1 SECOND * a.duration * 60)",
            nativeQuery = true)
    List<Appointment> findOverlappingByMedicalOffice(Integer medicalOfficeId, LocalDateTime startTime, LocalDateTime endTime);

    @Query(value = "SELECT * FROM appointment a WHERE a.doctor_id = :doctorId " +
            "AND a.enabled = true AND a.status NOT IN ('CANCELADA', 'COMPLETADA') " +
            "AND (a.appointment_date < :endTime AND :startTime < a.appointment_date + INTERVAL 1 SECOND * a.duration * 60)",
            nativeQuery = true)
    List<Appointment> findOverlappingByDoctor(Integer doctorId, LocalDateTime startTime, LocalDateTime endTime);

    @Query(value = "SELECT * FROM appointment a WHERE a.patient_id = :patientId " +
            "AND a.enabled = true AND a.status NOT IN ('CANCELADA', 'COMPLETADA') " +
            "AND DATE(a.appointment_date) = :date " +
            "AND ((a.appointment_date < :endTime AND :startTime < a.appointment_date + INTERVAL 1 SECOND * a.duration * 60) " +
            "OR (a.appointment_date + INTERVAL 1 SECOND * a.duration * 60 + INTERVAL 2 HOUR > :startTime))",
            nativeQuery = true)
    List<Appointment> findOverlappingOrCloseByPatient(Integer patientId, LocalDateTime startTime, LocalDateTime endTime, LocalDate date);

    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.doctor.id = :doctorId " +
            "AND a.enabled = true AND a.status NOT IN ('CANCELADA', 'COMPLETADA') " +
            "AND DATE(a.appointmentDate) = :date")
    Integer countAppointmentsByDoctorAndDate(Integer doctorId, LocalDate date);

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId " +
            "AND a.enabled = true AND a.status NOT IN ('CANCELADA', 'COMPLETADA') " +
            "AND DATE(a.appointmentDate) = :date")
    List<Appointment> findByDoctorIdAndDate(Integer doctorId, LocalDate date);

    List<Appointment> findByAppointmentDateBetweenAndEnabledTrue(LocalDateTime start, LocalDateTime end);

    List<Appointment> findByMedicalOfficeAndEnabledTrue(MedicalOffice medicalOffice);

    List<Appointment> findByDoctorAndEnabledTrue(Doctor doctor);
}