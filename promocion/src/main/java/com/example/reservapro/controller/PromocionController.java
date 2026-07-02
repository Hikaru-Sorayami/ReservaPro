package com.example.reservapro.controller;

import com.example.reservapro.Model.Promocion;
import com.example.reservapro.service.PromocionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promociones")
public class PromocionController {
    private final PromocionService service;

    public PromocionController(PromocionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Promocion> crear(@Valid @RequestBody Promocion entidad) {
        return ResponseEntity.ok(service.guardar(entidad));
    }

    @GetMapping
    public List<Promocion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promocion> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promocion> actualizar(@PathVariable Long id, @Valid @RequestBody Promocion entidad) {
        return ResponseEntity.ok(service.actualizar(id, entidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Registro eliminado");
    }
}

