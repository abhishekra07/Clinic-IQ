package com.codelabs.cliniciq.core.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClinicResponse {
    private Long id;
    private String name;
    private String code;
    private String address;
    private String email;
    private String phone;
}