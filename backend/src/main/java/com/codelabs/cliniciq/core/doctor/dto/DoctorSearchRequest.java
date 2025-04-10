package com.codelabs.cliniciq.core.doctor.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DoctorSearchRequest {
    private String firstName;
    private String lastName;
    private String specialization;
    private Long clinicId;
    private Integer page = 0;
    private Integer size = 10;
}