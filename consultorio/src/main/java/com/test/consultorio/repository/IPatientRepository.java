package com.test.consultorio.repository;

import com.test.consultorio.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepository extends PagingAndSortingRepository<Patient, Integer>,
        JpaRepository<Patient, Integer>,
        JpaSpecificationExecutor<Patient> {
}
