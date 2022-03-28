package com.healthcare.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Patient {
    // appointments, test results,

    @Id
    @GeneratedValue
    private Long patientId;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    @ManyToMany(mappedBy = "patients")
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "patient")
    private List<MedicalTest> medicalTests;

}
