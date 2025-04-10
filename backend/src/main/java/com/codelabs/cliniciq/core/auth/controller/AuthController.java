package com.codelabs.cliniciq.core.auth.controller;

import com.codelabs.cliniciq.core.auth.dto.AuthResponse;
import com.codelabs.cliniciq.core.auth.dto.LoginRequest;
import com.codelabs.cliniciq.core.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        log.info("Login request received for user: {}", request.getUsername());
        AuthResponse response = authService.login(request);
        log.info("Login successful for user: {}", request.getUsername());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody Map<String, String> payload) {
        String refreshToken = payload.get("refreshToken");
        log.info("Refresh token request received");
        AuthResponse response = authService.refreshToken(refreshToken);
        log.info("Token refresh successful");
        return ResponseEntity.ok(response);
    }
}