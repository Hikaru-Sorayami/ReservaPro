package com.example.reservapro.service;

import com.example.reservapro.Model.Disponibilidad;
import com.example.reservapro.repository.DisponibilidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisponibilidadService {
    private final DisponibilidadRepository repository;

    public DisponibilidadService(DisponibilidadRepository repository) {
        this.repository = repository;
    }

    public Disponibilidad guardar(Disponibilidad entidad) {
        return repository.save(entidad);
    }

    public List<Disponibilidad> listar() {
        return repository.findAll();
    }

    public Optional<Disponibilidad> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Disponibilidad actualizar(Long id, Disponibilidad datos) {
        Disponibilidad entidad = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Registro no encontrado"));
        datos.setId(entidad.getId());
        datos.setFechaCreacion(entidad.getFechaCreacion());
        return repository.save(datos);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}

