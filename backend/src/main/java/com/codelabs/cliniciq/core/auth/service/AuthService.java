package com.codelabs.cliniciq.core.auth.service;

import com.codelabs.cliniciq.common.security.jwt.JwtService;
import com.codelabs.cliniciq.core.auth.dto.AuthResponse;
import com.codelabs.cliniciq.core.auth.dto.LoginRequest;
import com.codelabs.cliniciq.core.user.repository.UserRepository;
import com.codelabs.cliniciq.core.user.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        log.debug("Authentication successful for user: {}", userDetails.getUsername());

        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        log.info("Tokens generated for user: {}", userDetails.getUsername());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthResponse refreshToken(String refreshToken) {
        log.debug("Extracting username from refresh token");
        String username = jwtService.extractUsername(refreshToken);
        log.debug("Username extracted: {}", username);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        log.debug("Loaded user details for token refresh");

        if (jwtService.isTokenValid(refreshToken, userDetails)) {
            log.info("Refresh token is valid for user: {}", username);
            String newAccessToken = jwtService.generateAccessToken(userDetails);

            return AuthResponse.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(refreshToken) // or generate new if required
                    .build();
        } else {
            log.warn("Invalid refresh token for user: {}", username);
            throw new RuntimeException("Invalid refresh token");
        }
    }
}