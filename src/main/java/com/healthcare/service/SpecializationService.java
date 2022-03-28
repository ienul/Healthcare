package com.healthcare.service;

import com.healthcare.dto.SpecializationDto;
import com.healthcare.mapper.SpecializationMapper;
import com.healthcare.model.Specialization;
import com.healthcare.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private SpecializationMapper specializationMapper;


    public void createSpecialization(SpecializationDto specializationDto){

       Specialization specialization = specializationMapper.map(specializationDto);
       specializationRepository.save(specialization);

    }
}
