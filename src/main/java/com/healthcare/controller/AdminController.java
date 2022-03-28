package com.healthcare.controller;

import com.healthcare.dto.SpecializationDto;
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


}
