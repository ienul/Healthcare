package com.healthcare.service;

import com.healthcare.dto.PatientDto;
import com.healthcare.mapper.PatientMapper;
import com.healthcare.model.Patient;
import com.healthcare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    public void createPatient(PatientDto patientDto){

        Patient patient = patientMapper.map(patientDto);
        patientRepository.save(patient);

    }


}
