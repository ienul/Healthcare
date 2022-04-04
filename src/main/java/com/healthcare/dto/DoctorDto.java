package com.healthcare.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DoctorDto {

    private String name;
    private String pricePerHour;
    private String specialization;
    private String id;

    private List<String> availableSpecializations;

}
