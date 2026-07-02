package com.example.reservapro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InicioController {
    @GetMapping("/")
    public String inicio() {
        return "Microservicio opinion ReservaPro funcionando. Usa /opiniones para consultar la API.";
    }
}

