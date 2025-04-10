package com.codelabs.cliniciq.core.patient.repository;

import com.codelabs.cliniciq.core.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByIsDeletedFalse();
    Optional<Patient> findByIdAndIsDeletedFalse(Long id);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}