package com.example.reservapro.controller;

import com.example.reservapro.Model.EstadoPago;
import com.example.reservapro.Model.EstadoReserva;
import com.example.reservapro.Model.Reserva;
import com.example.reservapro.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@Valid @RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.guardar(reserva));
    }

    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerReserva(@PathVariable Long id) {
        return reservaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Reserva> listarPorUsuario(@PathVariable Long usuarioId) {
        return reservaService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/servicio/{servicioId}")
    public List<Reserva> listarPorServicio(@PathVariable Long servicioId) {
        return reservaService.listarPorServicio(servicioId);
    }

    @GetMapping("/estado/{estadoReserva}")
    public List<Reserva> listarPorEstado(@PathVariable EstadoReserva estadoReserva) {
        return reservaService.listarPorEstado(estadoReserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> modificarReserva(@PathVariable Long id, @Valid @RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.modificar(id, reserva));
    }

    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<Reserva> confirmarReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.confirmar(id));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Reserva> cancelarReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.cancelar(id));
    }

    @PatchMapping("/{id}/pago")
    public ResponseEntity<Reserva> actualizarPago(@PathVariable Long id, @RequestParam EstadoPago estadoPago) {
        return ResponseEntity.ok(reservaService.actualizarPago(id, estadoPago));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReserva(@PathVariable Long id) {
        reservaService.eliminar(id);
        return ResponseEntity.ok("Reserva eliminada");
    }
}
