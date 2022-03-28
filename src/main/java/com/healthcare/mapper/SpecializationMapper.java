package com.healthcare.mapper;

import com.healthcare.dto.SpecializationDto;
import com.healthcare.model.Specialization;
import org.springframework.stereotype.Service;

@Service
public class SpecializationMapper {

    public Specialization map(SpecializationDto specializationDto){

        Specialization specialization = new Specialization();
        specialization.setName(specializationDto.getName());
        return specialization;
    }

}
