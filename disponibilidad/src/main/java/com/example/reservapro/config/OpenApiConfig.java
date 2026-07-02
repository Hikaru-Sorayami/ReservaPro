package com.example.reservapro.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "ReservaPro - Microservicio disponibilidad",
                version = "1.0.0",
                description = "API REST del microservicio disponibilidad para el sistema ReservaPro"
        )
)
public class OpenApiConfig {
}

