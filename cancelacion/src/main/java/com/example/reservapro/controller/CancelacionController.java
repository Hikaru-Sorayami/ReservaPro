package com.example.reservapro.controller;

import com.example.reservapro.Model.Cancelacion;
import com.example.reservapro.service.CancelacionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cancelaciones")
public class CancelacionController {
    private final CancelacionService service;

    public CancelacionController(CancelacionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cancelacion> crear(@Valid @RequestBody Cancelacion entidad) {
        return ResponseEntity.ok(service.guardar(entidad));
    }

    @GetMapping
    public List<Cancelacion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cancelacion> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cancelacion> actualizar(@PathVariable Long id, @Valid @RequestBody Cancelacion entidad) {
        return ResponseEntity.ok(service.actualizar(id, entidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Registro eliminado");
    }
}

