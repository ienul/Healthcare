package com.healthcare.service;

import com.healthcare.dto.DoctorDto;
import com.healthcare.model.Specialization;
import com.healthcare.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorDtoBuilder {

    @Autowired
    private SpecializationRepository specializationRepository;

    public DoctorDto defaultDoctorDto(){
        List<Specialization> specializations = specializationRepository.findAll();
        List<String> specializationNames = specializations.stream()
                .map(Specialization::getName)
                .collect(Collectors.toList());

        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setAvailableSpecializations(specializationNames);
        return doctorDto;
    }
}
