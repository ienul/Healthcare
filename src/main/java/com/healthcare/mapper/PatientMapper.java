package com.healthcare.mapper;

import com.healthcare.dto.PatientDto;
import com.healthcare.model.Patient;
import com.healthcare.model.Role;
import com.healthcare.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PatientMapper {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Patient map(PatientDto patientDto){

        Patient patient = new Patient();
        patient.setName(patientDto.getName());

        //daca voiam sa avem format LocalDate:
        LocalDate localDateOfBirth = LocalDate.parse(patientDto.getDateOfBirth());
        patient.setDateOfBirth(localDateOfBirth);
        User user = buildUser(patientDto);
        patient.setUser(user);

        return patient;

    }

    private User buildUser(PatientDto patientDto) {
        User user = new User();
        user.setEmail(patientDto.getEmail());
        user.setPassword(patientDto.getPassword());
        user.setRole(Role.PATIENT);
        encodePassword(user);
        return user;
    }

    public PatientDto map(Patient patient){

        PatientDto patientDto = new PatientDto();
        patientDto.setName(patient.getName());
        patientDto.setDateOfBirth(String.valueOf(patient.getDateOfBirth()));
        patientDto.setId(patient.getPatientId());

        return patientDto;

    }

    private void encodePassword(User user) {
        String passwordInPlainText = user.getPassword();
        String passwordEncoded = bCryptPasswordEncoder.encode(passwordInPlainText);
        user.setPassword(passwordEncoded);
    }

}
