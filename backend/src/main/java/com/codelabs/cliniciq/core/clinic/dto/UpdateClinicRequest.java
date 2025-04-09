package com.codelabs.cliniciq.core.clinic.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class UpdateClinicRequest {
    private String name;
    private String address;
    private String email;
    private String phone;
}
