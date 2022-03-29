package com.healthcare.controller;

import com.healthcare.dto.DoctorDto;
import com.healthcare.dto.SpecializationDto;
import com.healthcare.service.DoctorDtoBuilder;
import com.healthcare.service.DoctorService;
import com.healthcare.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorDtoBuilder doctorDtoBuilder;

    @GetMapping("/specialization")
    public String createSpecializationGet(Model model) {
        SpecializationDto specializationDto = new SpecializationDto();
        model.addAttribute("specializationDto", specializationDto);
        return "createSpecialization";
    }
    @PostMapping("/specialization")
    public String createSpecializationPost(Model model, @ModelAttribute("specializationDto") SpecializationDto specializationDto) {
        System.out.println(specializationDto.getName());
        specializationService.createSpecialization(specializationDto);
        return "redirect:/admin/specialization";
    }

///////////

    @GetMapping("/doctor")
    public String createDoctorGet(Model model) {
        DoctorDto doctorDto = doctorDtoBuilder.defaultDoctorDto();
        model.addAttribute("doctorDto", doctorDto);
        return "createDoctor";
    }

    @PostMapping("/doctor")
    public String createDoctorPost(Model model, @ModelAttribute("doctorDto") DoctorDto doctorDto) {
        System.out.println(doctorDto.getName());
        System.out.println(doctorDto.getPricePerHour());
        doctorService.createDoctor(doctorDto);
        return "redirect:/admin/doctor";
    }



}
