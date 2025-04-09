package com.codelabs.cliniciq.core.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UpdateUserRequest {
    private Long clinicId; // optional
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Set<String> roleNames; // e.g. ["DOCTOR", "CLINIC_ADMIN"]
}