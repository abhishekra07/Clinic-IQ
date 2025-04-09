package com.codelabs.cliniciq.core.tenant.repository;

import com.codelabs.cliniciq.core.tenant.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    boolean existsByCode(String code);
}