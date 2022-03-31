package com.healthcare.mapper;

import com.healthcare.dto.PatientDto;
import com.healthcare.model.Patient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PatientMapper {
    public Patient map(PatientDto patientDto){

        Patient patient = new Patient();
        patient.setName(patientDto.getName());

        //daca voiam sa avem format LocalDate:
        LocalDate localDateOfBirth = LocalDate.parse(patientDto.getDateOfBirth());

       patient.setDateOfBirth(localDateOfBirth);

        return patient;

    }

    public PatientDto map(Patient patient){

        PatientDto patientDto = new PatientDto();
        patientDto.setName(patient.getName());
        patientDto.setDateOfBirth(String.valueOf(patient.getDateOfBirth()));
        patientDto.setId(patient.getPatientId());


        return patientDto;

    }

}
