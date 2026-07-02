package com.example.reservapro.controller;

import com.example.reservapro.Model.Disponibilidad;
import com.example.reservapro.service.DisponibilidadService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disponibilidades")
public class DisponibilidadController {
    private final DisponibilidadService service;

    public DisponibilidadController(DisponibilidadService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Disponibilidad> crear(@Valid @RequestBody Disponibilidad entidad) {
        return ResponseEntity.ok(service.guardar(entidad));
    }

    @GetMapping
    public List<Disponibilidad> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disponibilidad> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disponibilidad> actualizar(@PathVariable Long id, @Valid @RequestBody Disponibilidad entidad) {
        return ResponseEntity.ok(service.actualizar(id, entidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Registro eliminado");
    }
}

