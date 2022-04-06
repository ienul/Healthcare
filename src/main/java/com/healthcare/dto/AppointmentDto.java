package com.healthcare.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AppointmentDto {

    private String doctorId;
    private String doctorName;
    private String date;
    private String time;
    private String medicalIssue;


    private List<String> availableSlots;





}
