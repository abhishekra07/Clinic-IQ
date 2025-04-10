package com.codelabs.cliniciq.core.doctor.entity;

import com.codelabs.cliniciq.common.entity.BaseEntity;
import com.codelabs.cliniciq.core.clinic.entity.Clinic;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false)
    private Clinic clinic;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String specialization;

    private String phone;

    private String email;

    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private String nationality;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "language_spoken")
    private String languageSpoken;

    @Column(name = "telemedicine_enabled")
    private Boolean telemedicineEnabled = false;

    @Column(name = "video_consult_url", length = 500)
    private String videoConsultUrl;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "license_issuing_authority")
    private String licenseIssuingAuthority;

    @Column(columnDefinition = "TEXT")
    private String qualifications;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}