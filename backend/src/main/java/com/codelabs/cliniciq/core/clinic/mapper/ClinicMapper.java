package com.codelabs.cliniciq.core.clinic.mapper;

import com.codelabs.cliniciq.core.clinic.dto.ClinicResponse;
import com.codelabs.cliniciq.core.clinic.entity.Clinic;
import org.springframework.stereotype.Component;

@Component
public class ClinicMapper {

    public ClinicResponse toDto(Clinic clinic) {
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