package com.example.reservapro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InicioController {
    @GetMapping("/")
    public String inicio() {
        return "Microservicio historial ReservaPro funcionando. Usa /historial-reservas para consultar la API.";
    }
}

