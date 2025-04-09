package com.codelabs.cliniciq.core.user.mapper;

import com.codelabs.cliniciq.core.user.dto.UserResponse;
import com.codelabs.cliniciq.core.user.entity.Role;
import com.codelabs.cliniciq.core.user.entity.User;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserResponse toDto(User user) {
        var dto = new UserResponse();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setClinicId(user.getClinic() != null ? user.getClinic().getId() : null);
        dto.setRoles(user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet()));
        return dto;
    }
}