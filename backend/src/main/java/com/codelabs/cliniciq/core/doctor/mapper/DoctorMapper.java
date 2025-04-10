package com.codelabs.cliniciq.core.doctor.mapper;

import com.codelabs.cliniciq.core.doctor.dto.DoctorResponse;
import com.codelabs.cliniciq.core.doctor.entity.Doctor;

public class DoctorMapper {

    public static DoctorResponse toDto(Doctor doctor) {
        if (doctor == null) {
            return null;
        }

        return DoctorResponse.builder()
                .id(doctor.getId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .email(doctor.getEmail())
                .phone(doctor.getPhone())
                .specialization(doctor.getSpecialization())
                .clinicId(doctor.getClinic() != null ? doctor.getClinic().getId() : null)
                .gender(doctor.getGender())
                .dateOfBirth(doctor.getDateOfBirth())
                .nationality(doctor.getNationality())
                .imageUrl(doctor.getImageUrl())
                .languageSpoken(doctor.getLanguageSpoken())
                .telemedicineEnabled(doctor.getTelemedicineEnabled())
                .videoConsultUrl(doctor.getVideoConsultUrl())
                .bio(doctor.getBio())
                .licenseNumber(doctor.getLicenseNumber())
                .licenseIssuingAuthority(doctor.getLicenseIssuingAuthority())
                .qualifications(doctor.getQualifications())
                .yearsOfExperience(doctor.getYearsOfExperience())
                .address(doctor.getAddress())
                .build();
    }
}