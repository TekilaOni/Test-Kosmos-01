package com.test.consultorio.repository;

import com.test.consultorio.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoctorRepository extends PagingAndSortingRepository<Doctor, Integer>,
        JpaRepository<Doctor, Integer>,
        JpaSpecificationExecutor<Doctor> {
}
