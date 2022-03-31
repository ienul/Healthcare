package com.healthcare.repository;

import com.healthcare.model.MedicalTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalTestRepository extends JpaRepository<MedicalTest, Long> {

}