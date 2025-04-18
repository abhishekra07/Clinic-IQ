package com.codelabs.cliniciq.core.patient.dto;

import com.codelabs.cliniciq.common.validation.ValidPhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Data
public class CreatePatientRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    private String gender;

    private LocalDate dateOfBirth;

    @ValidPhoneNumber
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Clinic ID is required")
    private Long clinicId;

    private String address;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private String emergencyContactName;

    @ValidPhoneNumber
    private String emergencyContactPhone;

    private String bloodGroup;

    private String medicalHistory;

    private String imageUrl;
}