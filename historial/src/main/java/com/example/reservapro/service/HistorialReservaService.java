package com.example.reservapro.service;

import com.example.reservapro.Model.HistorialReserva;
import com.example.reservapro.repository.HistorialReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialReservaService {
    private final HistorialReservaRepository repository;

    public HistorialReservaService(HistorialReservaRepository repository) {
        this.repository = repository;
    }

    public HistorialReserva guardar(HistorialReserva entidad) {
        return repository.save(entidad);
    }

    public List<HistorialReserva> listar() {
        return repository.findAll();
    }

    public Optional<HistorialReserva> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public HistorialReserva actualizar(Long id, HistorialReserva datos) {
        HistorialReserva entidad = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Registro no encontrado"));
        datos.setId(entidad.getId());
        datos.setFechaCreacion(entidad.getFechaCreacion());
        return repository.save(datos);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}

