package com.test.consultorio.service.imp;

import com.test.consultorio.entity.Speciality;
import com.test.consultorio.exception.NotFoundException;
import com.test.consultorio.repository.ISpecialityRepository;
import com.test.consultorio.service.ISpecialityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpecialityService implements ISpecialityService {

    private final ISpecialityRepository iSpecialityRepository;

    public Speciality findSpecialityById(Integer id){
        Speciality speciality = iSpecialityRepository.findById(id)
                .orElseThrow(()->new NotFoundException("ESPECIALIDAD NO ENCONTRADA"));
        return speciality;
    }
}
