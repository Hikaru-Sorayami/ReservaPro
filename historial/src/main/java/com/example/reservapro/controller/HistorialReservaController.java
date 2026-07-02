package com.example.reservapro.controller;

import com.example.reservapro.Model.HistorialReserva;
import com.example.reservapro.service.HistorialReservaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial-reservas")
public class HistorialReservaController {
    private final HistorialReservaService service;

    public HistorialReservaController(HistorialReservaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HistorialReserva> crear(@Valid @RequestBody HistorialReserva entidad) {
        return ResponseEntity.ok(service.guardar(entidad));
    }

    @GetMapping
    public List<HistorialReserva> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialReserva> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialReserva> actualizar(@PathVariable Long id, @Valid @RequestBody HistorialReserva entidad) {
        return ResponseEntity.ok(service.actualizar(id, entidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Registro eliminado");
    }
}

