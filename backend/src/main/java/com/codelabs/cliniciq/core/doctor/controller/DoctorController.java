package com.codelabs.cliniciq.core.doctor.controller;

import com.codelabs.cliniciq.core.doctor.dto.CreateDoctorRequest;
import com.codelabs.cliniciq.core.doctor.dto.DoctorResponse;
import com.codelabs.cliniciq.core.doctor.dto.DoctorSearchRequest;
import com.codelabs.cliniciq.core.doctor.dto.UpdateDoctorRequest;
import com.codelabs.cliniciq.core.doctor.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
@Slf4j
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponse> createDoctor(@Valid @RequestBody CreateDoctorRequest request) {
        log.info("Received request to create doctor");
        return ResponseEntity.ok(doctorService.createDoctor(request));
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DoctorResponse> updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody UpdateDoctorRequest request) {
        log.debug("Received request to update doctor with ID: {}", id);
        return ResponseEntity.ok(doctorService.updateDoctor(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<Page<DoctorResponse>> searchDoctors(@RequestBody DoctorSearchRequest request) {
        log.info("Received search request for doctors with pagination: page {}, size {}", request.getPage(), request.getSize());
        return ResponseEntity.ok(doctorService.searchDoctors(request));
    }
}