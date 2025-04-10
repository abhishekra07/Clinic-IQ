package com.codelabs.cliniciq.core.doctor.repository;

import com.codelabs.cliniciq.core.doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>, JpaSpecificationExecutor<Doctor> {
    List<Doctor> findAllByIsDeletedFalse();
    Optional<Doctor> findByIdAndIsDeletedFalse(Long id);
}