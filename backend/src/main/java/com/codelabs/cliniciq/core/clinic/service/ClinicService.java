package com.codelabs.cliniciq.core.clinic.service;

import com.codelabs.cliniciq.core.clinic.dto.ClinicResponse;
import com.codelabs.cliniciq.core.clinic.dto.CreateClinicRequest;
import com.codelabs.cliniciq.core.clinic.dto.UpdateClinicRequest;

import java.util.List;

public interface ClinicService {
    ClinicResponse createClinic(CreateClinicRequest request);
    List<ClinicResponse> getAllClinics();
    ClinicResponse getClinicById(Long id);
    ClinicResponse updateClinic(Long id, UpdateClinicRequest request);
    void deleteClinic(Long id);
}