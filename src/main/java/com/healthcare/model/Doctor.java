package com.healthcare.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Doctor {
    //availability (appointment), appointments, pricePerHour, name, specialization, patient list,
    @Id
    @GeneratedValue
    private Long doctorId;

    private String name;
    @ManyToOne
    @JoinColumn
    private Specialization specialization;
    private Float pricePerHour;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @ManyToMany
    @JoinTable(name = "doctor_patient", joinColumns = @JoinColumn(name = "doctorId"),
            inverseJoinColumns = @JoinColumn(name = "patientId"))
    private List<Patient> patients;

    @OneToMany(mappedBy = "doctor")
    private List<Holiday> holidays;

}
