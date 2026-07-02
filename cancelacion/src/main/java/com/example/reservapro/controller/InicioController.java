package com.example.reservapro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InicioController {
    @GetMapping("/")
    public String inicio() {
        return "Microservicio cancelacion ReservaPro funcionando. Usa /cancelaciones para consultar la API.";
    }
}

