package com.test.consultorio.repository;

import com.test.consultorio.entity.Appointment;
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

    @Query("SELECT a FROM Appointment a WHERE a.medicalOffice.id = :medicalOfficeId " +
            "AND a.enabled = true AND a.status NOT IN ('CANCELADA', 'COMPLETADA') " +
            "AND (a.appointmentDateTime < :endTime AND :startTime < a.appointmentDateTime + INTERVAL '1 SECOND' * a.durationMinutes * 60)")
    List<Appointment> findOverlappingByMedicalOffice(Integer medicalOfficeId, LocalDateTime startTime, LocalDateTime endTime);
    
}


