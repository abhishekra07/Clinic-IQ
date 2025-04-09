package com.codelabs.cliniciq.core.clinic.service;

import com.codelabs.cliniciq.common.exception.EntityNotFoundException;
import com.codelabs.cliniciq.common.exception.ResourceAlreadyExistsException;
import com.codelabs.cliniciq.core.clinic.dto.ClinicResponse;
import com.codelabs.cliniciq.core.clinic.dto.CreateClinicRequest;
import com.codelabs.cliniciq.core.clinic.dto.UpdateClinicRequest;
import com.codelabs.cliniciq.core.clinic.entity.Clinic;
import com.codelabs.cliniciq.core.clinic.mapper.ClinicMapper;
import com.codelabs.cliniciq.core.clinic.repository.ClinicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicServiceImpl implements ClinicService {

    private final ClinicMapper clinicMapper;

    private final ClinicRepository clinicRepository;

    public ClinicServiceImpl(ClinicMapper clinicMapper, ClinicRepository clinicRepository) {
        this.clinicMapper = clinicMapper;
        this.clinicRepository = clinicRepository;
    }

    @Override
    public ClinicResponse createClinic(CreateClinicRequest request) {
        if (clinicRepository.existsByCode(request.getCode())) {
            throw new ResourceAlreadyExistsException("Clinic code with code " + request.getCode() + " already exists.");
        }

        Clinic clinic = Clinic.builder()
                .name(request.getName())
                .code(request.getCode())
                .address(request.getAddress())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();

        clinic = clinicRepository.save(clinic);

        return clinicMapper.toDto(clinic);
    }

    @Override
    public List<ClinicResponse> getAllClinics() {
        return clinicRepository.findAllByIsDeletedFalse()
                .stream()
                .map(clinicMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClinicResponse getClinicById(Long id) {
        Clinic clinic = clinicRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Clinic with ID " + id + " not found"));
        return clinicMapper.toDto(clinic);
    }

    @Override
    @Transactional
    public ClinicResponse updateClinic(Long id, UpdateClinicRequest request) {
        Clinic clinic = clinicRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Clinic with ID " + id + " not found"));

        if (request.getName() != null) clinic.setName(request.getName());
        if (request.getAddress() != null) clinic.setAddress(request.getAddress());
        if (request.getEmail() != null) clinic.setEmail(request.getEmail());
        if (request.getPhone() != null) clinic.setPhone(request.getPhone());

        clinicRepository.save(clinic);
        return clinicMapper.toDto(clinic);
    }

    @Override
    @Transactional
    public void deleteClinic(Long id) {
        Clinic clinic = clinicRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Clinic with ID " + id + " not found"));
        clinic.setDeleted(true);
        clinicRepository.save(clinic);
    }

}