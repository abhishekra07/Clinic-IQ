package com.codelabs.cliniciq.core.tenant.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClinicResponse {
    private Long id;
    private String name;
    private String code;
    private String address;
    private String email;
    private String phone;
}