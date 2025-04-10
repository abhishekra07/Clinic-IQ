package com.codelabs.cliniciq.core.patient.controller;

import com.codelabs.cliniciq.core.patient.dto.CreatePatientRequest;
import com.codelabs.cliniciq.core.patient.dto.PatientResponse;
import com.codelabs.cliniciq.core.patient.dto.UpdatePatientRequest;
import com.codelabs.cliniciq.core.patient.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(@Valid @RequestBody CreatePatientRequest request) {
        log.info("Received request to create patient with email {}", request.getEmail());
        PatientResponse response = patientService.createPatient(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getById(@PathVariable Long id) {
        log.info("Fetching patient by ID: {}", id);
        return ResponseEntity.ok(patientService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAll() {
        log.info("Fetching all patients");
        return ResponseEntity.ok(patientService.getAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PatientResponse> update(
            @PathVariable Long id,
            @RequestBody UpdatePatientRequest request
    ) {
        log.info("Updating patient with ID: {}", id);
        return ResponseEntity.ok(patientService.updatePatient(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        log.info("Soft deleting patient with ID: {}", id);
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}