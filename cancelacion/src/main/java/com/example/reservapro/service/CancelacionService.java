package com.example.reservapro.service;

import com.example.reservapro.Model.Cancelacion;
import com.example.reservapro.repository.CancelacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancelacionService {
    private final CancelacionRepository repository;

    public CancelacionService(CancelacionRepository repository) {
        this.repository = repository;
    }

    public Cancelacion guardar(Cancelacion entidad) {
        return repository.save(entidad);
    }

    public List<Cancelacion> listar() {
        return repository.findAll();
    }

    public Optional<Cancelacion> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Cancelacion actualizar(Long id, Cancelacion datos) {
        Cancelacion entidad = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Registro no encontrado"));
        datos.setId(entidad.getId());
        datos.setFechaCreacion(entidad.getFechaCreacion());
        return repository.save(datos);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}

