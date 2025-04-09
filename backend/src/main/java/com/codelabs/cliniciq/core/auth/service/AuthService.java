package com.codelabs.cliniciq.core.auth.service;

import com.codelabs.cliniciq.common.security.jwt.JwtService;
import com.codelabs.cliniciq.core.auth.dto.AuthResponse;
import com.codelabs.cliniciq.core.auth.dto.LoginRequest;
import com.codelabs.cliniciq.core.user.repository.UserRepository;
import com.codelabs.cliniciq.core.user.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public AuthResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        return AuthResponse.builder()
                .accessToken(jwtService.generateAccessToken(userDetails))
                .refreshToken(jwtService.generateRefreshToken(userDetails))
                .build();
    }

    public AuthResponse refreshToken(String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtService.isTokenValid(refreshToken, userDetails)) {
            return AuthResponse.builder()
                    .accessToken(jwtService.generateAccessToken(userDetails))
                    .refreshToken(refreshToken) // or generate a new refresh token
                    .build();
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }
}