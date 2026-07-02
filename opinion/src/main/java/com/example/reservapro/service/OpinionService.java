package com.example.reservapro.service;

import com.example.reservapro.Model.Opinion;
import com.example.reservapro.repository.OpinionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OpinionService {
    private final OpinionRepository repository;

    public OpinionService(OpinionRepository repository) {
        this.repository = repository;
    }

    public Opinion guardar(Opinion entidad) {
        return repository.save(entidad);
    }

    public List<Opinion> listar() {
        return repository.findAll();
    }

    public Optional<Opinion> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Opinion actualizar(Long id, Opinion datos) {
        Opinion entidad = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Registro no encontrado"));
        datos.setId(entidad.getId());
        datos.setFechaCreacion(entidad.getFechaCreacion());
        return repository.save(datos);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}

