package com.codelabs.cliniciq.core.doctor.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateDoctorRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String specialization;

    private Long clinicId; // Optional: allow reassigning to another clinic

    private String gender;

    private LocalDate dateOfBirth;

    private String nationality;

    private String imageUrl;

    private String languageSpoken;

    private Boolean telemedicineEnabled;

    private String videoConsultUrl;

    private String bio;

    private String licenseNumber;

    private String licenseIssuingAuthority;

    private String qualifications;

    private Integer yearsOfExperience;

    private String address;
}