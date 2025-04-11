package com.codelabs.cliniciq.common.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Custom annotation to enable CORS globally
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(CorsConfig.class)  // Automatically imports the CorsConfig class
public @interface EnableCors {
}