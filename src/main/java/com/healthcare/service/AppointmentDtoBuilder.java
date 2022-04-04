package com.healthcare.service;

import com.healthcare.dto.AppointmentDto;
import com.healthcare.model.Appointment;
import com.healthcare.model.Doctor;
import com.healthcare.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentDtoBuilder {

    @Autowired
    private DoctorRepository doctorRepository;

    public AppointmentDto createDefaultDto(String doctorId) {

        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setDoctorId(doctorId);

        return appointmentDto;
    }

    public Optional<AppointmentDto> createDtoWithTimeIntervals(String doctorId, String date) {

        Optional<Doctor> optionalDoctor = doctorRepository.findById(Long.valueOf(doctorId));
        if (optionalDoctor.isEmpty()){
            return Optional.empty();
        }
        LocalDate localDate = LocalDate.parse(date);
        Doctor doctor = optionalDoctor.get();

        List<LocalTime> allPossibleStartingHours = generateAllPossibleStartingHours();
        List<LocalTime> unavailableStartingHours = extractUnavailableStartingHours(doctor, localDate);
        List<LocalTime> availableStartingHours = computeAvailableStartingHours(allPossibleStartingHours, unavailableStartingHours);
        List<String> availableAppointmentSlots = generateAppointmentSlots(availableStartingHours);
        return buildAppointmentDto(doctorId, date, doctor, availableAppointmentSlots);
    }

    private List<LocalTime> computeAvailableStartingHours(List<LocalTime> allPossibleStartingHours, List<LocalTime> unavailableStartingHours) {
        return allPossibleStartingHours.stream()
                .filter(element -> !unavailableStartingHours.contains(element))
                .collect(Collectors.toList());

    }

    private Optional<AppointmentDto> buildAppointmentDto(String doctorId, String date, Doctor doctor, List<String> availableAppointmentSlots) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setDoctorId(doctorId);
        appointmentDto.setDate(date);
        appointmentDto.setAvailableSlots(availableAppointmentSlots);
        appointmentDto.setDoctorName(doctor.getName());
        return Optional.of(appointmentDto);
    }

    private List<String> generateAppointmentSlots(List<LocalTime> availableStartingHours) {
        List<String> result = new ArrayList<>();
        for (LocalTime time : availableStartingHours){
            String startTime = time.toString();
            String endTime = time.plusMinutes(30).toString();
            result.add(startTime + " - " + endTime);
        }
        return result;
    }

    private List<LocalTime> generateAllPossibleStartingHours() {
        List<LocalTime> result = new ArrayList<>();
        for (LocalTime time = LocalTime.of(8,0); time.isBefore(LocalTime.of(16,0)); time = time.plusMinutes(30)){
            result.add(time);
        }

        return result;
    }


    private List<LocalTime> extractUnavailableStartingHours(Doctor doctor, LocalDate localDate) {

        return doctor.getAppointments().stream()
                .filter(appointment -> appointment.getDateTime().toLocalDate().equals(localDate))
                .map(Appointment::getDateTime)
                .map(LocalDateTime::toLocalTime)
                .collect(Collectors.toList());
    }

}
