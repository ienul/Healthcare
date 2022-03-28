package com.healthcare.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity

public class Specialization {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
