package com.healthcare.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PatientDto {

    private String name;
    private String dateOfBirth;
    private Long id;
    private String email;
    private String password;


}
