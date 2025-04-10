package com.codelabs.cliniciq.core.patient.mapper;

import com.codelabs.cliniciq.core.patient.dto.CreatePatientRequest;
import com.codelabs.cliniciq.core.patient.dto.PatientResponse;
import com.codelabs.cliniciq.core.patient.entity.Patient;
import com.codelabs.cliniciq.core.clinic.entity.Clinic;

public class PatientMapper {

    public static Patient toEntity(CreatePatientRequest request, Clinic clinic) {
        return Patient.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .dateOfBirth(request.getDateOfBirth())
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .postalCode(request.getPostalCode())
                .emergencyContactName(request.getEmergencyContactName())
                .emergencyContactPhone(request.getEmergencyContactPhone())
                .bloodGroup(request.getBloodGroup())
                .medicalHistory(request.getMedicalHistory())
                .imageUrl(request.getImageUrl())
                .clinic(clinic)
                .build();
    }

    public static PatientResponse toDto(Patient patient) {
        return PatientResponse.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .gender(patient.getGender())
                .dateOfBirth(patient.getDateOfBirth())
                .phone(patient.getPhone())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .city(patient.getCity())
                .state(patient.getState())
                .country(patient.getCountry())
                .postalCode(patient.getPostalCode())
                .emergencyContactName(patient.getEmergencyContactName())
                .emergencyContactPhone(patient.getEmergencyContactPhone())
                .bloodGroup(patient.getBloodGroup())
                .medicalHistory(patient.getMedicalHistory())
                .imageUrl(patient.getImageUrl())
                .clinicId(patient.getClinic().getId())
                .build();
    }
}