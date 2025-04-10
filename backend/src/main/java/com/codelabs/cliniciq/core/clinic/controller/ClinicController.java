package com.codelabs.cliniciq.core.clinic.controller;

import com.codelabs.cliniciq.core.clinic.dto.ClinicResponse;
import com.codelabs.cliniciq.core.clinic.dto.CreateClinicRequest;
import com.codelabs.cliniciq.core.clinic.dto.UpdateClinicRequest;
import com.codelabs.cliniciq.core.clinic.service.ClinicService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/clinics")
public class ClinicController {

    private static final Logger logger = LoggerFactory.getLogger(ClinicController.class);
    private final ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @PostMapping
    public ResponseEntity<ClinicResponse> createClinic(@Valid @RequestBody CreateClinicRequest request) {
        logger.info("Creating clinic with code: {}", request.getCode());
        return ResponseEntity.ok(clinicService.createClinic(request));
    }

    @GetMapping
    public ResponseEntity<List<ClinicResponse>> getAllClinics() {
        logger.info("Fetching all clinics");
        return ResponseEntity.ok(clinicService.getAllClinics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicResponse> getClinic(@PathVariable Long id) {
        logger.info("Fetching clinic by ID: {}", id);
        return ResponseEntity.ok(clinicService.getClinicById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClinicResponse> updateClinic(@PathVariable Long id, @RequestBody UpdateClinicRequest request) {
        logger.info("Updating clinic with ID: {}", id);
        return ResponseEntity.ok(clinicService.updateClinic(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable Long id) {
        logger.info("Soft deleting clinic with ID: {}", id);
        clinicService.deleteClinic(id);
        return ResponseEntity.noContent().build();
    }
}
