package com.codelabs.cliniciq.core.clinic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class CreateClinicRequest {

    @NotBlank(message = "Clinic name is required")
    @Size(min = 2, max = 100, message = "Clinic name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Clinic code is required")
    @Size(min = 3, max = 50, message = "Code must be between 3 and 50 characters")
    private String code;

    private String address;

    @Email(message = "Invalid email format")
    private String email;

    private String phone;
}