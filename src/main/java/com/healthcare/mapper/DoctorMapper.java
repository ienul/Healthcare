package com.healthcare.mapper;

import com.healthcare.dto.DoctorDto;
import com.healthcare.model.Doctor;
import com.healthcare.model.Specialization;
import com.healthcare.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorMapper {

    @Autowired
    private SpecializationRepository specializationRepository;

    public Doctor map(DoctorDto doctorDto) {

        Doctor doctor = new Doctor();
        doctor.setName(doctorDto.getName());
        doctor.setPricePerHour(Float.valueOf(doctorDto.getPricePerHour()));
        String specializationName = String.valueOf(doctorDto.getSpecialization());

        // List<Specialization> specializationOfTheDoctor = specializationRepository.findByName(specializationName);

//        doctor.setSpecialization(specializationOfTheDoctor.get(0)); // doctor se asteapta la o specializare, nu la o lista de specializari. Deaia avem get(0) aplicat listei

        Specialization specializationOfTheDoctor = specializationRepository.findOneByName(specializationName);

        doctor.setSpecialization(specializationOfTheDoctor); // doctor se asteapta la o specializare, nu la o lista de specializari. Deaia avem get(0) aplicat listei

        return doctor;
    }

    public DoctorDto map(Doctor doctor) {

        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setName(doctor.getName());
        doctorDto.setPricePerHour(String.valueOf(Math.round(doctor.getPricePerHour())));
        doctorDto.setSpecialization(doctor.getSpecialization().getName());
        doctorDto.setId(String.valueOf(doctor.getDoctorId()));

        return doctorDto;
    }


    public List<DoctorDto> map(List<Doctor> doctors) {

        List<DoctorDto> result = new ArrayList<>();

        for (Doctor doctor : doctors) {

            DoctorDto doctorDto = map(doctor);
            result.add(doctorDto);
        }

        return result;
    }
}
