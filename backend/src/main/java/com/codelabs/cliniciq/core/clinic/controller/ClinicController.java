package com.codelabs.cliniciq.core.clinic.controller;

import com.codelabs.cliniciq.core.clinic.dto.ClinicResponse;
import com.codelabs.cliniciq.core.clinic.dto.CreateClinicRequest;
import com.codelabs.cliniciq.core.clinic.dto.UpdateClinicRequest;
import com.codelabs.cliniciq.core.clinic.service.ClinicService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clinics")
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

    @GetMapping("/{id}")
    public ResponseEntity<ClinicResponse> getClinic(@PathVariable Long id) {
        return ResponseEntity.ok(clinicService.getClinicById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClinicResponse> updateClinic(@PathVariable Long id, @RequestBody UpdateClinicRequest request) {
        return ResponseEntity.ok(clinicService.updateClinic(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable Long id) {
        clinicService.deleteClinic(id);
        return ResponseEntity.noContent().build();
    }
}
