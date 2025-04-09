package com.codelabs.cliniciq.core.tenant.service;

import com.codelabs.cliniciq.core.tenant.dto.ClinicResponse;
import com.codelabs.cliniciq.core.tenant.dto.CreateClinicRequest;
import com.codelabs.cliniciq.core.tenant.entity.Clinic;
import com.codelabs.cliniciq.core.tenant.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    public ClinicResponse createClinic(CreateClinicRequest request) {
        if (clinicRepository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException("Clinic code already exists.");
        }

        Clinic clinic = Clinic.builder()
                .name(request.getName())
                .code(request.getCode())
                .address(request.getAddress())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();

        clinic = clinicRepository.save(clinic);

        return toDto(clinic);
    }

    public List<ClinicResponse> getAllClinics() {
        return clinicRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ClinicResponse toDto(Clinic clinic) {
        return ClinicResponse.builder()
                .id(clinic.getId())
                .name(clinic.getName())
                .code(clinic.getCode())
                .address(clinic.getAddress())
                .email(clinic.getEmail())
                .phone(clinic.getPhone())
                .build();
    }
}