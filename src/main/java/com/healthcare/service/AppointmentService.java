package com.healthcare.service;

import com.healthcare.dto.AppointmentDto;
import com.healthcare.mapper.AppointmentMapper;
import com.healthcare.model.Appointment;
import com.healthcare.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void createAppointment(AppointmentDto appointmentDto, String authenticatedUserEmail) {

        Optional<Appointment> optionalAppointment= appointmentMapper.map(appointmentDto, authenticatedUserEmail);
        if(optionalAppointment.isEmpty()){
            return;
        }
        Appointment appointment = optionalAppointment.get();
        appointmentRepository.save(appointment);

    }

    public List<AppointmentDto> getAppointmentsFor(String doctorEmail) {

        List<Appointment> appointments = appointmentRepository.findAppointmentsByDoctorUserEmail(doctorEmail);
        return appointmentMapper.map(appointments);
    }
}
