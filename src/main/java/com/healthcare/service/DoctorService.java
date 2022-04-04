package com.healthcare.service;


import com.healthcare.dto.DoctorDto;
import com.healthcare.mapper.DoctorMapper;
import com.healthcare.model.Doctor;
import com.healthcare.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorMapper doctorMapper;

    public void createDoctor(DoctorDto doctorDto){

        Doctor doctor = doctorMapper.map(doctorDto);
        doctorRepository.save(doctor);
    }

    public List<DoctorDto> getDoctorsList() {

        List<Doctor> doctors = doctorRepository.findAll();

        return  doctorMapper.map(doctors);

    }



}
