package com.codelabs.cliniciq.core.clinic.repository;

import com.codelabs.cliniciq.core.clinic.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    boolean existsByCode(String code);
    Optional<Clinic> findByIdAndIsDeletedFalse(Long id);
    List<Clinic> findAllByIsDeletedFalse();
}