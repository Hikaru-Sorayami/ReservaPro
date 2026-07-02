package com.example.reservapro.service;

import com.example.reservapro.Model.Promocion;
import com.example.reservapro.repository.PromocionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromocionService {
    private final PromocionRepository repository;

    public PromocionService(PromocionRepository repository) {
        this.repository = repository;
    }

    public Promocion guardar(Promocion entidad) {
        return repository.save(entidad);
    }

    public List<Promocion> listar() {
        return repository.findAll();
    }

    public Optional<Promocion> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Promocion actualizar(Long id, Promocion datos) {
        Promocion entidad = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Registro no encontrado"));
        datos.setId(entidad.getId());
        datos.setFechaCreacion(entidad.getFechaCreacion());
        return repository.save(datos);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}

