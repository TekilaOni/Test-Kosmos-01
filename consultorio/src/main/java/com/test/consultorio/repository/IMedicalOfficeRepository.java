package com.test.consultorio.repository;

import com.test.consultorio.entity.MedicalOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicalOfficeRepository extends PagingAndSortingRepository<MedicalOffice, Integer>,
        JpaRepository<MedicalOffice, Integer>,
        JpaSpecificationExecutor<MedicalOffice> {
}
