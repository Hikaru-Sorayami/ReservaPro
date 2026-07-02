package com.example.reservapro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InicioController {
    @GetMapping("/")
    public String inicio() {
        return "Microservicio reporte ReservaPro funcionando. Usa /reportes para consultar la API.";
    }
}

