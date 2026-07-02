package com.example.reservapro.controller;

import com.example.reservapro.Model.Opinion;
import com.example.reservapro.service.OpinionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opiniones")
public class OpinionController {
    private final OpinionService service;

    public OpinionController(OpinionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Opinion> crear(@Valid @RequestBody Opinion entidad) {
        return ResponseEntity.ok(service.guardar(entidad));
    }

    @GetMapping
    public List<Opinion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Opinion> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Opinion> actualizar(@PathVariable Long id, @Valid @RequestBody Opinion entidad) {
        return ResponseEntity.ok(service.actualizar(id, entidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Registro eliminado");
    }
}

