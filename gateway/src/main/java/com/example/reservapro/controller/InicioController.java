package com.example.reservapro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InicioController {
    @GetMapping("/")
    public String inicio() {
        return "API Gateway ReservaPro funcionando. Usa /api/usuarios, /api/reservas, /api/pagos y demas rutas configuradas.";
    }
}

