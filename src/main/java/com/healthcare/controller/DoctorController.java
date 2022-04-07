package com.healthcare.controller;

import com.healthcare.dto.AppointmentDto;
import com.healthcare.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointments")
    public String appointmentsGet(Model model, Authentication authentication) {

        List<AppointmentDto> appointmentDtos = appointmentService.getAppointmentsFor(authentication.getName());
        model.addAttribute("appointments", appointmentDtos);
        return "viewAppointments";
    }

}
