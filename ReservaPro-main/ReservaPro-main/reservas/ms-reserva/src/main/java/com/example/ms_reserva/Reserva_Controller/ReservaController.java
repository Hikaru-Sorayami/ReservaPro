package com.example.ms_reserva.Reserva_Controller;

import com.example.ms_reserva.Reserva_Service.ReservaService;
import com.example.ms_reserva.Reservas.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public String crearReserva(@RequestBody Reserva reserva) {

        boolean usuarioExiste = reservaService.validarUsuario(reserva.getUsuarioId());

        if (!usuarioExiste) {
            return "El usuario no existe";
        }

        return "Reserva creada correctamente";
    }
}