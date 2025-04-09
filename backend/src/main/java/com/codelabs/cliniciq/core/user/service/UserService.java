package com.codelabs.cliniciq.core.user.service;

import com.codelabs.cliniciq.common.exception.EntityNotFoundException;
import com.codelabs.cliniciq.core.clinic.entity.Clinic;
import com.codelabs.cliniciq.core.clinic.repository.ClinicRepository;
import com.codelabs.cliniciq.core.user.dto.CreateUserRequest;
import com.codelabs.cliniciq.core.user.dto.UpdateUserRequest;
import com.codelabs.cliniciq.core.user.dto.UserResponse;
import com.codelabs.cliniciq.core.user.entity.Role;
import com.codelabs.cliniciq.core.user.entity.User;
import com.codelabs.cliniciq.core.user.mapper.UserMapper;
import com.codelabs.cliniciq.core.user.repository.RoleRepository;
import com.codelabs.cliniciq.core.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ClinicRepository clinicRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        User user = new User();

        if (request.getClinicId() != null) {
            Clinic clinic = clinicRepository.findByIdAndIsDeletedFalse(request.getClinicId())
                    .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));
            user.setClinic(clinic);
        }
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        // Load roles from DB using role names from request
        Set<Role> roles = request.getRoleNames().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new EntityNotFoundException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        user.setRoles(roles);

        User saved = userRepository.save(user);
        return UserMapper.toDto(saved);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return UserMapper.toDto(user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAllByIsDeletedFalse()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }

    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Update fields if they are provided (non-null)
        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }

        // Update clinic if provided (can be null)
        if (request.getClinicId() != null) {
            Clinic clinic = clinicRepository.findByIdAndIsDeletedFalse(request.getClinicId())
                    .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));
            user.setClinic(clinic);
        }

        // Update roles if provided
        if (request.getRoleNames() != null && !request.getRoleNames().isEmpty()) {
            Set<Role> roles = request.getRoleNames().stream()
                    .map(roleName -> roleRepository.findByName(roleName)
                            .orElseThrow(() -> new EntityNotFoundException("Role not found: " + roleName)))
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }

        User updatedUser = userRepository.save(user);
        return UserMapper.toDto(updatedUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findByIdAndIsDeletedFalse(id)
                .filter(u -> !u.isDeleted())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setDeleted(true);
        userRepository.save(user);
    }
}
