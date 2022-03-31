package com.healthcare.mapper;

import com.healthcare.dto.MedicalTestDto;
import com.healthcare.model.MedicalTest;
import com.healthcare.model.Patient;
import com.healthcare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MedicalTestMapper {

    @Autowired
    private PatientRepository patientRepository;

    public MedicalTest map(MedicalTestDto medicalTestDto){

        MedicalTest medicalTest = new MedicalTest();
        medicalTest.setName(medicalTestDto.getName());
        medicalTest.setDateTime(LocalDateTime.parse(medicalTestDto.getDateTime()));
        medicalTest.setResult(medicalTestDto.getResult());
        medicalTest.setPatient(medicalTest.getPatient());
        Optional<Patient> optionalPatient = patientRepository.findById(medicalTestDto.getPatientId());//optional pentru ca atunci cand cautam in BDD, e posibil sa nu gasim.
        if (optionalPatient.isEmpty()){
            throw new RuntimeException("Patient not found");
        }
        Patient patient = optionalPatient.get();
        medicalTest.setPatient(patient);

        return medicalTest;
    }
}
