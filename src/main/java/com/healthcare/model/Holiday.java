package com.healthcare.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Holiday {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn
    private Doctor doctor;

}
