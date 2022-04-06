package com.healthcare.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Appointment {
    // date + time, patient, doctor, list of diagnosis,

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn
    private Patient patient;

    @ManyToOne
    @JoinColumn
    private Doctor doctor;


    private String medicalIssue;

    @ElementCollection(targetClass = Diagnostic.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private List<Diagnostic> diagnostics;


}
