package com.codelabs.cliniciq.core.tenant.controller;

import com.codelabs.cliniciq.core.tenant.dto.ClinicResponse;
import com.codelabs.cliniciq.core.tenant.dto.CreateClinicRequest;
import com.codelabs.cliniciq.core.tenant.service.ClinicService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinics")
public class ClinicController {

    private final ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @PostMapping
    public ResponseEntity<ClinicResponse> createClinic(@Valid @RequestBody CreateClinicRequest request) {
        return ResponseEntity.ok(clinicService.createClinic(request));
    }

    @GetMapping
    public ResponseEntity<List<ClinicResponse>> getAllClinics() {
        return ResponseEntity.ok(clinicService.getAllClinics());
    }
}
