package com.codelabs.cliniciq.core.patient.service;

import com.codelabs.cliniciq.common.exception.EntityNotFoundException;
import com.codelabs.cliniciq.core.clinic.entity.Clinic;
import com.codelabs.cliniciq.core.clinic.repository.ClinicRepository;
import com.codelabs.cliniciq.core.patient.dto.CreatePatientRequest;
import com.codelabs.cliniciq.core.patient.dto.PatientResponse;
import com.codelabs.cliniciq.core.patient.dto.UpdatePatientRequest;
import com.codelabs.cliniciq.core.patient.entity.Patient;
import com.codelabs.cliniciq.core.patient.mapper.PatientMapper;
import com.codelabs.cliniciq.core.patient.repository.PatientRepository;
import com.codelabs.cliniciq.core.user.entity.User;
import com.codelabs.cliniciq.core.user.repository.RoleRepository;
import com.codelabs.cliniciq.core.user.repository.UserRepository;
import com.codelabs.cliniciq.core.user.entity.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ClinicRepository clinicRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public PatientResponse createPatient(CreatePatientRequest request) {
        log.info("Creating patient for clinicId {}", request.getClinicId());

        Clinic clinic = clinicRepository.findByIdAndIsDeletedFalse(request.getClinicId())
                .orElseThrow(() -> {
                    log.warn("Clinic not found with id {}", request.getClinicId());
                    return new EntityNotFoundException("Clinic not found");
                });

        if (patientRepository.existsByEmail(request.getEmail())) {
            log.warn("Email already exists: {}", request.getEmail());
            throw new IllegalArgumentException("Patient with this email already exists.");
        }

        Patient patient = PatientMapper.toEntity(request, clinic);
        Patient savedPatient = patientRepository.save(patient);
        log.info("Patient created with id {}", savedPatient.getId());

        // Create user account for portal login
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setUsername(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setClinic(clinic);
        user.setRoles(Collections.singleton(getPatientRole()));
        userRepository.save(user);
        log.info("User account created for patient email {}", request.getEmail());

        return PatientMapper.toDto(savedPatient);
    }

    public PatientResponse getById(Long id) {
        Patient patient = patientRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + id));

        return PatientMapper.toDto(patient);
    }

    public List<PatientResponse> getAll() {
        return patientRepository.findAllByIsDeletedFalse()
                .stream()
                .map(PatientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public PatientResponse updatePatient(Long id, UpdatePatientRequest request) {
        Patient patient = patientRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        if (request.getFirstName() != null) patient.setFirstName(request.getFirstName());
        if (request.getLastName() != null) patient.setLastName(request.getLastName());
        if (request.getGender() != null) patient.setGender(request.getGender());
        if (request.getDateOfBirth() != null) patient.setDateOfBirth(request.getDateOfBirth());
        if (request.getAddress() != null) patient.setAddress(request.getAddress());
        if (request.getCity() != null) patient.setCity(request.getCity());
        if (request.getState() != null) patient.setState(request.getState());
        if (request.getCountry() != null) patient.setCountry(request.getCountry());
        if (request.getPostalCode() != null) patient.setPostalCode(request.getPostalCode());
        if (request.getEmergencyContactName() != null) patient.setEmergencyContactName(request.getEmergencyContactName());
        if (request.getEmergencyContactPhone() != null) patient.setEmergencyContactPhone(request.getEmergencyContactPhone());
        if (request.getBloodGroup() != null) patient.setBloodGroup(request.getBloodGroup());
        if (request.getMedicalHistory() != null) patient.setMedicalHistory(request.getMedicalHistory());
        if (request.getImageUrl() != null) patient.setImageUrl(request.getImageUrl());

        if (request.getClinicId() != null) {
            Clinic clinic = clinicRepository.findByIdAndIsDeletedFalse(request.getClinicId())
                    .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));
            patient.setClinic(clinic);
        }

        Patient updated = patientRepository.save(patient);
        log.info("Patient updated with ID: {}", updated.getId());

        return PatientMapper.toDto(updated);
    }

    @Transactional
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        patient.setDeleted(true);
        patientRepository.save(patient);
        log.info("Patient soft deleted with ID: {}", id);
    }

    private Role getPatientRole() {
        return roleRepository.findByName("PATIENT")
                .orElseThrow(() -> new EntityNotFoundException("Role PATIENT not found"));
    }
}
