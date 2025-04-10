package com.codelabs.cliniciq.core.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleResponse {
    private Long id;
    private String name;
}