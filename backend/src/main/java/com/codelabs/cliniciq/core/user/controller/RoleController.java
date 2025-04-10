package com.codelabs.cliniciq.core.user.controller;

import com.codelabs.cliniciq.core.user.dto.RoleResponse;
import com.codelabs.cliniciq.core.user.entity.Role;
import com.codelabs.cliniciq.core.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@Slf4j
public class RoleController {

    private final RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        log.info("Fetching all roles");
        List<Role> roles = roleRepository.findAll();
        log.debug("Retrieved {} roles", roles.size());
        List<RoleResponse> response = roles.stream()
                .map(role -> new RoleResponse(role.getId(), role.getName()))
                .toList();
        return ResponseEntity.ok(response);
    }
}