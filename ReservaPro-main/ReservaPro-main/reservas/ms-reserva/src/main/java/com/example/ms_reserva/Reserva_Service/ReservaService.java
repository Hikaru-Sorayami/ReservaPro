package com.example.ms_reserva.Reserva_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReservaService {

    @Autowired
    private RestTemplate restTemplate;

    public boolean validarUsuario(Long usuarioId) {
        String url = "http://localhost:8081/usuarios/" + usuarioId;

        try {
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}