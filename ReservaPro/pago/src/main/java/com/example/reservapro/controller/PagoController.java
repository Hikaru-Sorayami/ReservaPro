package com.example.reservapro.controller;

import com.example.reservapro.Model.EstadoPago;
import com.example.reservapro.Model.Pago;
import com.example.reservapro.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {
    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping
    public ResponseEntity<Pago> crearPago(@Valid @RequestBody Pago pago) {
        return ResponseEntity.ok(pagoService.guardar(pago));
    }

    @GetMapping
    public List<Pago> listarPagos() {
        return pagoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPago(@PathVariable Long id) {
        return pagoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Pago> listarPorUsuario(@PathVariable Long usuarioId) {
        return pagoService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/reserva/{reservaId}")
    public List<Pago> listarPorReserva(@PathVariable Long reservaId) {
        return pagoService.listarPorReserva(reservaId);
    }

    @GetMapping("/estado/{estadoPago}")
    public List<Pago> listarPorEstado(@PathVariable EstadoPago estadoPago) {
        return pagoService.listarPorEstado(estadoPago);
    }

    @GetMapping("/referencia/{referenciaTransaccion}")
    public ResponseEntity<Pago> obtenerPorReferencia(@PathVariable String referenciaTransaccion) {
        return pagoService.obtenerPorReferencia(referenciaTransaccion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizarPago(@PathVariable Long id, @Valid @RequestBody Pago pago) {
        return ResponseEntity.ok(pagoService.actualizar(id, pago));
    }

    @PatchMapping("/{id}/aprobar")
    public ResponseEntity<Pago> aprobarPago(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.aprobar(id));
    }

    @PatchMapping("/{id}/rechazar")
    public ResponseEntity<Pago> rechazarPago(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.rechazar(id));
    }

    @PatchMapping("/{id}/anular")
    public ResponseEntity<Pago> anularPago(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.anular(id));
    }

    @PatchMapping("/{id}/reembolsar")
    public ResponseEntity<Pago> reembolsarPago(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.reembolsar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPago(@PathVariable Long id) {
        pagoService.eliminar(id);
        return ResponseEntity.ok("Pago eliminado");
    }
}
