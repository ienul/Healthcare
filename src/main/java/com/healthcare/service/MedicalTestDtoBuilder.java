package com.healthcare.service;

import com.healthcare.dto.MedicalTestDto;
import com.healthcare.dto.PatientDto;
import com.healthcare.mapper.PatientMapper;
import com.healthcare.model.Patient;
import com.healthcare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalTestDtoBuilder {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    public MedicalTestDto createDefaultMedicalTestDto(){

        MedicalTestDto medicalTestDto = new MedicalTestDto();

        List<Patient> patients = patientRepository.findAll();

        List<PatientDto> patientDtos = patients.stream()
                .map(patientMapper::map)
                .collect(Collectors.toList());
        medicalTestDto.setPatientDtos(patientDtos);

        return medicalTestDto;


    }

}
