package com.healthcare.controller;

import com.healthcare.dto.DoctorDto;
import com.healthcare.dto.MedicalTestDto;
import com.healthcare.dto.PatientDto;
import com.healthcare.dto.SpecializationDto;
import com.healthcare.service.*;
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

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicalTestDtoBuilder medicalTestDtoBuilder;

    @Autowired
    private MedicalTestService medicalTestService;


    @GetMapping("/specialization")
    public String specializationGet(Model model) {
        SpecializationDto specializationDto = new SpecializationDto();
        model.addAttribute("specializationDto", specializationDto);
        return "createSpecialization";
    }
    @PostMapping("/specialization")
    public String specializationPost(Model model, @ModelAttribute("specializationDto") SpecializationDto specializationDto) {
        System.out.println(specializationDto.getName());
        specializationService.createSpecialization(specializationDto);
        return "redirect:/admin/specialization";
    }

///////////

    @GetMapping("/doctor")
    public String doctorGet(Model model) {
        DoctorDto doctorDto = doctorDtoBuilder.defaultDoctorDto();
        model.addAttribute("doctorDto", doctorDto);
        return "createDoctor";
    }

    @PostMapping("/doctor")
    public String doctorPost(Model model, @ModelAttribute("doctorDto") DoctorDto doctorDto) {
        System.out.println(doctorDto.getName());
        System.out.println(doctorDto.getPricePerHour());
        System.out.println(doctorDto.getSpecialization());
        doctorService.createDoctor(doctorDto);
        return "redirect:/admin/doctor";
    }

///////

    @GetMapping("/patient")
    public String patientGet(Model model) {
        PatientDto patientDto = new PatientDto();
        model.addAttribute("patientDto", patientDto);
        return "createPatient";
    }
    @PostMapping("/patient")
    public String patientPost(Model model, @ModelAttribute("patientDto") PatientDto patientDto) {
        System.out.println(patientDto.getName());
        System.out.println(patientDto.getDateOfBirth());
        patientService.createPatient(patientDto);
        return "redirect:/admin/patient";
    }

    @GetMapping("/medicalTest")
    public String medicalTestGet(Model model) {
        MedicalTestDto medicalTestDto = medicalTestDtoBuilder.createDefaultMedicalTestDto();
        model.addAttribute("medicalTestDto", medicalTestDto);
        return  "medicalTest";
    }

/////
    @PostMapping("/medicalTest")
    public String medicalTestPost(Model model, @ModelAttribute("medicalTestDto") MedicalTestDto medicalTestDto) {
        System.out.println(medicalTestDto);
        medicalTestService.createMedicalTest(medicalTestDto);
        return "redirect:/admin/medicalTest";
    }




}
