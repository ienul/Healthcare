package com.healthcare.service;

import com.healthcare.dto.MedicalTestDto;
import com.healthcare.mapper.MedicalTestMapper;
import com.healthcare.model.MedicalTest;
import com.healthcare.repository.MedicalTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalTestService {

    @Autowired
    private MedicalTestMapper medicalTestMapper;

    @Autowired
    private MedicalTestRepository medicalTestRepository;

    public void createMedicalTest(MedicalTestDto medicalTestDto){

        MedicalTest medicalTest = medicalTestMapper.map(medicalTestDto);
        medicalTestRepository.save(medicalTest);

    }


}
