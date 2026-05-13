package com.example.ms_reserva.Reserva_Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ReservaConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}