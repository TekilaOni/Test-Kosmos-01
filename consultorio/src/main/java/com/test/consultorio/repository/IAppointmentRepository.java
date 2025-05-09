package com.test.consultorio.repository;

import com.test.consultorio.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppointmentRepository extends PagingAndSortingRepository<Appointment, Integer>,
        JpaRepository<Appointment, Integer>,
        JpaSpecificationExecutor<Appointment> {
}
