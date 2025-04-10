package com.codelabs.cliniciq.core.patient.dto;

import com.codelabs.cliniciq.common.validation.ValidPhoneNumber;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdatePatientRequest {

    private Long clinicId;

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate dateOfBirth;

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