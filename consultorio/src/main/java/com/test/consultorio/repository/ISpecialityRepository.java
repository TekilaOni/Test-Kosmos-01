package com.test.consultorio.repository;

import com.test.consultorio.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpecialityRepository extends PagingAndSortingRepository<Speciality, Integer>,
        JpaRepository<Speciality, Integer>,
        JpaSpecificationExecutor<Speciality>{
}
