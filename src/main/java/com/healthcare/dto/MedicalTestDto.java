package com.healthcare.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MedicalTestDto {

    private String name;
    private String dateTime;
    private String result;
    private List<PatientDto> patientDtos;
    private Long patientId;

}
