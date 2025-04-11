package com.codelabs.cliniciq;

import com.codelabs.cliniciq.common.config.EnableCors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCors
@SpringBootApplication
public class CliniciqApplication {
	public static void main(String[] args) {
		SpringApplication.run(CliniciqApplication.class, args);
	}
}
