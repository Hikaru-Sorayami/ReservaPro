package com.example.reservapro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InicioController {

    @GetMapping("/")
    public String inicio() {
        return "Microservicio de usuarios ReservaPro funcionando. Usa /usuarios para consultar la API.";
    }
}
