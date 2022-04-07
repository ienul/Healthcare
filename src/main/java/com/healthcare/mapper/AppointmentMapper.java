package com.healthcare.mapper;

import com.healthcare.dto.AppointmentDto;
import com.healthcare.model.Appointment;
import com.healthcare.model.Doctor;
import com.healthcare.model.Patient;
import com.healthcare.repository.DoctorRepository;
import com.healthcare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentMapper {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    public Optional<Appointment> map(AppointmentDto appointmentDto, String authenticatedUserEmail){
        Appointment appointment = new Appointment();

        Optional<Doctor> optionalDoctor = doctorRepository.findById(Long.valueOf(appointmentDto.getDoctorId()));
        if(optionalDoctor.isEmpty()){
            return Optional.empty();
        }
        appointment.setDoctor(optionalDoctor.get());

        ///////////// pe baza patientId TREBUIE SA OBTIN UN OBIECT DE TIP PATIENT DIN BAZA DE DATE, EXACT CA IN EXEMPLUL CU DOCTORUL///////////

        Optional<Patient> optionalPatient = patientRepository.findByUserEmail(authenticatedUserEmail);
        if(optionalPatient.isEmpty()){
            return Optional.empty();
        }
        appointment.setPatient(optionalPatient.get());


        LocalDateTime localDateTime = parseLocalDateTime(appointmentDto);
        appointment.setDateTime(localDateTime);

        appointment.setMedicalIssue(appointmentDto.getMedicalIssue());



        return Optional.of(appointment);

    }

    private LocalDateTime parseLocalDateTime(AppointmentDto appointmentDto) {
        LocalDate localDate = LocalDate.parse(appointmentDto.getDate());
        LocalTime localTime = parseTime(appointmentDto);
        LocalDateTime localDateTime = localDate.atTime(localTime);
        return localDateTime;
    }

    private LocalTime parseTime(AppointmentDto appointmentDto) {

        String startTime = appointmentDto.getTime().split(" - ")[0];

        LocalTime localTime = LocalTime.parse(startTime);
        return localTime;
    }

    public List<AppointmentDto> map(List<Appointment> appointments){
        List<AppointmentDto> result = new ArrayList<>();
        for(Appointment appointment: appointments){
            AppointmentDto appointmentDto = map(appointment);
            result.add(appointmentDto);
        }
        return result;
    }

    public AppointmentDto map(Appointment appointment){
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setDate(String.valueOf(appointment.getDateTime().toLocalDate()));
        appointmentDto.setTime(String.valueOf(appointment.getDateTime().toLocalTime()));
        appointmentDto.setDoctorName(appointment.getDoctor().getName());
        appointmentDto.setMedicalIssue(appointment.getMedicalIssue());
        appointmentDto.setPatientName(appointment.getPatient().getName());
        return appointmentDto;
    }

}
