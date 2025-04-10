package com.codelabs.cliniciq.core.doctor.service;

import com.codelabs.cliniciq.common.exception.EntityNotFoundException;
import com.codelabs.cliniciq.core.clinic.entity.Clinic;
import com.codelabs.cliniciq.core.clinic.repository.ClinicRepository;
import com.codelabs.cliniciq.core.doctor.dto.CreateDoctorRequest;
import com.codelabs.cliniciq.core.doctor.dto.DoctorResponse;
import com.codelabs.cliniciq.core.doctor.dto.DoctorSearchRequest;
import com.codelabs.cliniciq.core.doctor.dto.UpdateDoctorRequest;
import com.codelabs.cliniciq.core.doctor.entity.Doctor;
import com.codelabs.cliniciq.core.doctor.mapper.DoctorMapper;
import com.codelabs.cliniciq.core.doctor.repository.DoctorRepository;
import com.codelabs.cliniciq.core.doctor.specification.DoctorSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ClinicRepository clinicRepository;

    @Transactional
    public DoctorResponse createDoctor(CreateDoctorRequest request) {
        log.info("Creating doctor for clinicId {}", request.getClinicId());

        Clinic clinic = clinicRepository.findByIdAndIsDeletedFalse(request.getClinicId())
                .orElseThrow(() -> {
                    log.warn("Clinic not found with Id : {}", request.getClinicId());
                    return new EntityNotFoundException("Clinic not found with Id : " + request.getClinicId());
                });

        Doctor doctor = Doctor.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .specialization(request.getSpecialization())
                .gender(request.getGender())
                .dateOfBirth(request.getDateOfBirth())
                .nationality(request.getNationality())
                .imageUrl(request.getImageUrl())
                .languageSpoken(request.getLanguageSpoken())
                .telemedicineEnabled(request.getTelemedicineEnabled() != null && request.getTelemedicineEnabled())
                .videoConsultUrl(request.getVideoConsultUrl())
                .bio(request.getBio())
                .licenseNumber(request.getLicenseNumber())
                .licenseIssuingAuthority(request.getLicenseIssuingAuthority())
                .qualifications(request.getQualifications())
                .yearsOfExperience(request.getYearsOfExperience())
                .address(request.getAddress())
                .clinic(clinic)
                .build();

        Doctor saved = doctorRepository.save(doctor);
        log.info("Doctor created with id {}", saved.getId());

        return DoctorMapper.toDto(saved);
    }

    public List<DoctorResponse> getAllDoctors() {
        return doctorRepository.findAllByIsDeletedFalse()
                .stream().map(DoctorMapper::toDto).toList();
    }

    public DoctorResponse getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
        return DoctorMapper.toDto(doctor);
    }

    @Transactional
    public DoctorResponse updateDoctor(Long id, UpdateDoctorRequest request) {
        log.info("Updating doctor with ID: {}", id);

        Doctor doctor = doctorRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> {
                    log.warn("Doctor not found with ID: {}", id);
                    return new EntityNotFoundException("Doctor not found");
                });

        if (request.getFirstName() != null) {
            doctor.setFirstName(request.getFirstName());
            log.debug("Updated first name for doctor ID {}: {}", id, request.getFirstName());
        }

        if (request.getLastName() != null) {
            doctor.setLastName(request.getLastName());
            log.debug("Updated last name for doctor ID {}: {}", id, request.getLastName());
        }

        if (request.getEmail() != null) {
            doctor.setEmail(request.getEmail());
            log.debug("Updated email for doctor ID {}: {}", id, request.getEmail());
        }

        if (request.getPhone() != null) {
            doctor.setPhone(request.getPhone());
            log.debug("Updated phone for doctor ID {}: {}", id, request.getPhone());
        }

        if (request.getSpecialization() != null) {
            doctor.setSpecialization(request.getSpecialization());
            log.debug("Updated specialization for doctor ID {}: {}", id, request.getSpecialization());
        }

        if (request.getClinicId() != null) {
            Clinic clinic = clinicRepository.findByIdAndIsDeletedFalse(request.getClinicId())
                    .orElseThrow(() -> {
                        log.warn("Clinic not found with ID: {}", request.getClinicId());
                        return new EntityNotFoundException("Clinic not found");
                    });
            doctor.setClinic(clinic);
            log.debug("Updated clinic for doctor ID {}: {}", id, clinic.getName());
        }

        if (request.getGender() != null) {
            doctor.setGender(request.getGender());
            log.debug("Updated gender for doctor ID {}: {}", id, request.getGender());
        }

        if (request.getDateOfBirth() != null) {
            doctor.setDateOfBirth(request.getDateOfBirth());
            log.debug("Updated DOB for doctor ID {}: {}", id, request.getDateOfBirth());
        }

        if (request.getNationality() != null) {
            doctor.setNationality(request.getNationality());
            log.debug("Updated nationality for doctor ID {}: {}", id, request.getNationality());
        }

        if (request.getImageUrl() != null) {
            doctor.setImageUrl(request.getImageUrl());
            log.debug("Updated image URL for doctor ID {}: {}", id, request.getImageUrl());
        }

        if (request.getLanguageSpoken() != null) {
            doctor.setLanguageSpoken(request.getLanguageSpoken());
            log.debug("Updated language spoken for doctor ID {}: {}", id, request.getLanguageSpoken());
        }

        if (request.getTelemedicineEnabled() != null) {
            doctor.setTelemedicineEnabled(request.getTelemedicineEnabled());
            log.debug("Updated telemedicineEnabled for doctor ID {}: {}", id, request.getTelemedicineEnabled());
        }

        if (request.getVideoConsultUrl() != null) {
            doctor.setVideoConsultUrl(request.getVideoConsultUrl());
            log.debug("Updated video consult URL for doctor ID {}: {}", id, request.getVideoConsultUrl());
        }

        if (request.getBio() != null) {
            doctor.setBio(request.getBio());
            log.debug("Updated bio for doctor ID {}: {}", id);
        }

        if (request.getLicenseNumber() != null) {
            doctor.setLicenseNumber(request.getLicenseNumber());
            log.debug("Updated license number for doctor ID {}: {}", id, request.getLicenseNumber());
        }

        if (request.getLicenseIssuingAuthority() != null) {
            doctor.setLicenseIssuingAuthority(request.getLicenseIssuingAuthority());
            log.debug("Updated license authority for doctor ID {}: {}", id, request.getLicenseIssuingAuthority());
        }

        if (request.getQualifications() != null) {
            doctor.setQualifications(request.getQualifications());
            log.debug("Updated qualifications for doctor ID {}: {}", id);
        }

        if (request.getYearsOfExperience() != null) {
            doctor.setYearsOfExperience(request.getYearsOfExperience());
            log.debug("Updated years of experience for doctor ID {}: {}", id, request.getYearsOfExperience());
        }

        if (request.getAddress() != null) {
            doctor.setAddress(request.getAddress());
            log.debug("Updated address for doctor ID {}: {}", id);
        }

        Doctor updated = doctorRepository.save(doctor);
        log.info("Doctor with ID: {} updated successfully", id);

        return DoctorMapper.toDto(updated);
    }

    @Transactional
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
        doctor.setDeleted(true);
        doctorRepository.save(doctor);
        log.info("Doctor soft-deleted with id {}", id);
    }

    public Page<DoctorResponse> searchDoctors(DoctorSearchRequest request) {
        log.info("Searching doctors with filters: {}", request);

        Specification<Doctor> spec = Specification
                .where(DoctorSpecification.notDeleted())
                .and(DoctorSpecification.firstNameContains(request.getFirstName()))
                .and(DoctorSpecification.lastNameContains(request.getLastName()))
                .and(DoctorSpecification.specializationEquals(request.getSpecialization()))
                .and(DoctorSpecification.clinicIdEquals(request.getClinicId()));

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());

        Page<Doctor> resultPage = doctorRepository.findAll(spec, pageRequest);

        return resultPage.map(DoctorMapper::toDto);
    }
}