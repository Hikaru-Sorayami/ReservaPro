package com.example.reservapro.service;

import com.example.reservapro.Model.Servicio;
import com.example.reservapro.repository.ServicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {
    private final ServicioRepository repository;

    public ServicioService(ServicioRepository repository) {
        this.repository = repository;
    }

    public Servicio guardar(Servicio entidad) {
        return repository.save(entidad);
    }

    public List<Servicio> listar() {
        return repository.findAll();
    }

    public Optional<Servicio> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Servicio actualizar(Long id, Servicio datos) {
        Servicio entidad = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Registro no encontrado"));
        datos.setId(entidad.getId());
        datos.setFechaCreacion(entidad.getFechaCreacion());
        return repository.save(datos);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}

