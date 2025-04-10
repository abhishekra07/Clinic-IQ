package com.codelabs.cliniciq.core.doctor.specification;

import com.codelabs.cliniciq.core.doctor.entity.Doctor;
import org.springframework.data.jpa.domain.Specification;

public class DoctorSpecification {

    public static Specification<Doctor> firstNameContains(String firstName) {
        return (root, query, cb) -> firstName == null ? null : cb.like(cb.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
    }

    public static Specification<Doctor> lastNameContains(String lastName) {
        return (root, query, cb) -> lastName == null ? null : cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    }

    public static Specification<Doctor> specializationEquals(String specialty) {
        return (root, query, cb) -> specialty == null ? null : cb.equal(cb.lower(root.get("specialization")), specialty.toLowerCase());
    }

    public static Specification<Doctor> clinicIdEquals(Long clinicId) {
        return (root, query, cb) -> clinicId == null ? null : cb.equal(root.get("clinic").get("id"), clinicId);
    }

    public static Specification<Doctor> notDeleted() {
        return (root, query, cb) -> cb.isFalse(root.get("isDeleted"));
    }
}