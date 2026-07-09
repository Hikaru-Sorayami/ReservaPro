package com.example.reservapro.client;

import com.example.reservapro.dto.ReservaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="reserva")
public interface ReservaClient {
    @GetMapping("/reservas/{id}")
    ReservaDTO obtenerReserva(@PathVariable("id") Long id);

    @PutMapping("/reservas/{id}/confirmar")
    void confirmarPago(@PathVariable("id") Long id);

}
