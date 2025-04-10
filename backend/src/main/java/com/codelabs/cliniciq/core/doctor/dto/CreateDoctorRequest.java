package com.codelabs.cliniciq.core.doctor.dto;

import com.codelabs.cliniciq.common.validation.ValidPhoneNumber;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateDoctorRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @ValidPhoneNumber
    private String phone;

    private String specialization;

    @NotNull(message = "Clinic ID is required")
    private Long clinicId;

    @Pattern(regexp = "^(Male|Female|Other)?$", message = "Gender must be Male, Female or Other")
    private String gender;

    private LocalDate dateOfBirth;

    private String nationality;

    @Size(max = 500, message = "Image URL must be less than 500 characters")
    private String imageUrl;

    private String languageSpoken;

    private Boolean telemedicineEnabled = false;

    @Size(max = 500, message = "Video consult URL must be less than 500 characters")
    private String videoConsultUrl;

    @Size(max = 1000, message = "Bio must be less than 1000 characters")
    private String bio;

    private String licenseNumber;

    private String licenseIssuingAuthority;

    private String qualifications;

    @Min(value = 0, message = "Experience must be positive")
    private Integer yearsOfExperience;

    private String address;
}