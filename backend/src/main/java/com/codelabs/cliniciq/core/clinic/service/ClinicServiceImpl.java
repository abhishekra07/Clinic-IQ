package com.codelabs.cliniciq.core.clinic.service;

import com.codelabs.cliniciq.common.exception.EntityNotFoundException;
import com.codelabs.cliniciq.common.exception.ResourceAlreadyExistsException;
import com.codelabs.cliniciq.core.clinic.dto.ClinicResponse;
import com.codelabs.cliniciq.core.clinic.dto.CreateClinicRequest;
import com.codelabs.cliniciq.core.clinic.dto.UpdateClinicRequest;
import com.codelabs.cliniciq.core.clinic.entity.Clinic;
import com.codelabs.cliniciq.core.clinic.mapper.ClinicMapper;
import com.codelabs.cliniciq.core.clinic.repository.ClinicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicServiceImpl implements ClinicService {

    private static final Logger logger = LoggerFactory.getLogger(ClinicServiceImpl.class);

    private final ClinicMapper clinicMapper;

    private final ClinicRepository clinicRepository;

    public ClinicServiceImpl(ClinicMapper clinicMapper, ClinicRepository clinicRepository) {
        this.clinicMapper = clinicMapper;
        this.clinicRepository = clinicRepository;
    }

    @Override
    public ClinicResponse createClinic(CreateClinicRequest request) {
        logger.debug("Checking for existing clinic with code: {}", request.getCode());
        if (clinicRepository.existsByCode(request.getCode())) {
            logger.warn("Clinic already exists with code: {}", request.getCode());
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
        logger.info("Clinic created with ID: {}", clinic.getId());
        return clinicMapper.toDto(clinic);
    }

    @Override
    public List<ClinicResponse> getAllClinics() {
        logger.debug("Fetching all clinics that are not deleted");
        return clinicRepository.findAllByIsDeletedFalse()
                .stream()
                .map(clinicMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClinicResponse getClinicById(Long id) {
        logger.debug("Fetching clinic by ID: {}", id);
        Clinic clinic = clinicRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> {
                    logger.warn("Clinic not found with ID: {}", id);
                    return new EntityNotFoundException("Clinic with ID " + id + " not found");
                });
        return clinicMapper.toDto(clinic);
    }

    @Override
    @Transactional
    public ClinicResponse updateClinic(Long id, UpdateClinicRequest request) {
        logger.debug("Updating clinic with ID: {}", id);
        Clinic clinic = clinicRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> {
                    logger.warn("Clinic not found for update with ID: {}", id);
                    return new EntityNotFoundException("Clinic with ID " + id + " not found");
                });

        if (request.getName() != null) clinic.setName(request.getName());
        if (request.getAddress() != null) clinic.setAddress(request.getAddress());
        if (request.getEmail() != null) clinic.setEmail(request.getEmail());
        if (request.getPhone() != null) clinic.setPhone(request.getPhone());

        Clinic updated = clinicRepository.save(clinic);
        logger.info("Updated clinic with ID: {}", updated.getId());
        return clinicMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteClinic(Long id) {
        logger.debug("Soft deleting clinic with ID: {}", id);
        Clinic clinic = clinicRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> {
                    logger.warn("Clinic not found for deletion with ID: {}", id);
                    return new EntityNotFoundException("Clinic with ID " + id + " not found");
                });
        clinic.setDeleted(true);
        clinicRepository.save(clinic);
    }

}