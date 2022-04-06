package com.healthcare.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String password;

    @OneToOne(mappedBy = "user")
    private Doctor doctor;

    @OneToOne(mappedBy = "user")
    private Patient patient;

    @Enumerated(EnumType.STRING)
    private Role role;
}

