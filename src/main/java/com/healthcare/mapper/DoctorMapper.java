package com.healthcare.mapper;

import com.healthcare.dto.DoctorDto;
import com.healthcare.model.Doctor;
import org.springframework.stereotype.Service;

@Service
public class DoctorMapper {

    public Doctor map(DoctorDto doctorDto){

        Doctor doctor = new Doctor();
        doctor.setName(doctorDto.getName());
        doctor.setPricePerHour(Float.valueOf(doctorDto.getPricePerHour()));
        return doctor;

    }


}
