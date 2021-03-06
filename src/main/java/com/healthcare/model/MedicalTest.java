package com.healthcare.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class MedicalTest {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private LocalDateTime dateTime;

    private String result; // can be a number, boolean or description

    @ManyToOne
    @JoinColumn
    private Patient patient;

}
