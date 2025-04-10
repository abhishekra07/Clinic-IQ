package com.codelabs.cliniciq.common.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Component
public class MdcFilter extends OncePerRequestFilter {

    private final String CO_RELATION_ID_HEADER_KEY = "co-relation-id";
    private final String CO_RELATION_ID_MDC_KEY = "correlationId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            String cID= Optional.ofNullable(request.getHeaderNames()).flatMap(names -> Collections.list(names).stream()
                    .filter(name -> name.equalsIgnoreCase(CO_RELATION_ID_HEADER_KEY))
                    .findFirst()).orElse(UUID.randomUUID().toString());
            MDC.put(CO_RELATION_ID_MDC_KEY, cID);
            chain.doFilter(request, response);
        } finally {
            MDC.remove(CO_RELATION_ID_MDC_KEY);
        }
    }
}