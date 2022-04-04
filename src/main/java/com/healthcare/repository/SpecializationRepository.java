package com.healthcare.repository;

import com.healthcare.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

    List<Specialization> findByName(String name);
    Specialization findOneByName(String name);

}
