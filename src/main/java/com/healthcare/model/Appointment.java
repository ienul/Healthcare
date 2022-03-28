package com.healthcare.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
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

    @ElementCollection(targetClass = Diagnostic.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private List<Diagnostic> diagnostics;


}
