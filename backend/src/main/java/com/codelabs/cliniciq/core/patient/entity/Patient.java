package com.codelabs.cliniciq.core.patient.entity;

import com.codelabs.cliniciq.common.entity.BaseEntity;
import com.codelabs.cliniciq.core.clinic.entity.Clinic;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false)
    private Clinic clinic;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    private String gender;

    private String nationality;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    private String address;

    @Column(name = "emergency_contact")
    private String emergencyContact;

    @Column(name = "insurance_number")
    private String insuranceNumber;

    @Column(name = "insurance_provider")
    private String insuranceProvider;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}