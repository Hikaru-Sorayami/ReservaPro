package com.example.reservapro.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "ReservaPro - API Gateway",
                version = "1.0.0",
                description = "Gateway central para enrutar solicitudes hacia los microservicios ReservaPro"
        )
)
public class OpenApiConfig {
}

