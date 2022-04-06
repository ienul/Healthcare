package com.healthcare.controller;


import com.healthcare.dto.AppointmentDto;
import com.healthcare.dto.DoctorDto;
import com.healthcare.service.AppointmentDtoBuilder;
import com.healthcare.service.AppointmentService;
import com.healthcare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentDtoBuilder appointmentDtoBuilder;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/doctors")
    public String doctorsGet(Model model){
        System.out.println("S-a apelat Get pe /doctors");
        List<DoctorDto> doctorList = doctorService.getDoctorsList();
        System.out.println(doctorList);
        System.out.println("s-a printat doctorList");
        model.addAttribute("doctors", doctorList);

        return "viewDoctors";
    }
    @GetMapping("/doctors/{doctorId}")
    public String doctorGet(Model model, @PathVariable(value = "doctorId") String doctorId){
        System.out.println(doctorId);
        AppointmentDto appointmentDto = appointmentDtoBuilder.createDefaultDto(doctorId);

        model.addAttribute("appointment", appointmentDto);


        return "viewDoctor";
    }

    @PostMapping("/doctors/{doctorId}")
    public String doctorPost(Model model, @PathVariable(value = "doctorId") String doctorId, @ModelAttribute(name = "appointment") AppointmentDto appointmentDto){
        System.out.println(appointmentDto.getDate());
        System.out.println("Am reusit pasul 1!");
        if (appointmentDto.getDate().length() == 0){
            return "redirect:/patient/doctors/" + doctorId;
        }
        Optional<AppointmentDto> optionalAppointmentDto = appointmentDtoBuilder.createDtoWithTimeIntervals(doctorId,appointmentDto.getDate());
        if(optionalAppointmentDto.isEmpty()){
            return "redirect:/patient/doctors/" + doctorId;
        }
        model.addAttribute("appointment", optionalAppointmentDto.get());

        return "viewDoctor";
    }

    @PostMapping("/doctors/{doctorId}/appointment")
    public String doctorAppointmentPost(@PathVariable(value = "doctorId") String doctorId,
                                        @ModelAttribute(name = "appointment") AppointmentDto appointmentDto,
                                        Authentication authentication){
        System.out.println("Am reusit pasul 2!");
        System.out.println("Get Name este : " + authentication.getName());
        String authenticatedUserEmail = authentication.getName();
        appointmentService.createAppointment(appointmentDto, authenticatedUserEmail);

        return "redirect:/patient/doctors/" + doctorId;
    }

}