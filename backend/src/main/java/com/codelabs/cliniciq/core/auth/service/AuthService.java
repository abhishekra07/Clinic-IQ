package com.codelabs.cliniciq.core.auth.service;

import com.codelabs.cliniciq.common.security.jwt.JwtService;
import com.codelabs.cliniciq.core.auth.dto.AuthResponse;
import com.codelabs.cliniciq.core.auth.dto.LoginRequest;
import com.codelabs.cliniciq.core.user.entity.Role;
import com.codelabs.cliniciq.core.user.entity.User;
import com.codelabs.cliniciq.core.user.repository.UserRepository;
import com.codelabs.cliniciq.core.user.service.CustomUserDetails;
import com.codelabs.cliniciq.core.user.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public AuthResponse login(LoginRequest request) {
        log.debug("Authenticating user: {}", request.getUsername());
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = ((CustomUserDetails) auth.getPrincipal()).getUser();

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        log.debug("Authentication successful for user: {}", userDetails.getUsername());

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        log.info("Tokens generated for user: {}", userDetails.getUsername());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .name(user.getFirstName())
                .email(user.getEmail())
                .roles(user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList()))
                .build();
    }

    public AuthResponse refreshToken(String refreshToken) {
        log.debug("Extracting username from refresh token");
        String username = jwtService.extractUsername(refreshToken);
        log.debug("Username extracted: {}", username);

        User user = ((CustomUserDetails) userDetailsService.loadUserByUsername(username)).getUser();
        log.debug("Loaded user details for token refresh");

        if (jwtService.isTokenValid(refreshToken, user)) {
            log.info("Refresh token is valid for user: {}", username);

            String newAccessToken = jwtService.generateAccessToken(user);

            return AuthResponse.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(refreshToken)
                    .userId(user.getId())
                    .name(user.getFirstName())
                    .email(user.getEmail())
                    .roles(user.getRoles().stream()
                            .map(Role::getName)
                            .collect(Collectors.toList()))
                    .build();
        } else {
            log.warn("Invalid refresh token for user: {}", username);
            throw new RuntimeException("Invalid refresh token");
        }
    }
}