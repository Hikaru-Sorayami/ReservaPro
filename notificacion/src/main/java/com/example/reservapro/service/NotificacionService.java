package com.example.reservapro.service;

import com.example.reservapro.Model.Notificacion;
import com.example.reservapro.repository.NotificacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {
    private final NotificacionRepository repository;

    public NotificacionService(NotificacionRepository repository) {
        this.repository = repository;
    }

    public Notificacion guardar(Notificacion entidad) {
        return repository.save(entidad);
    }

    public List<Notificacion> listar() {
        return repository.findAll();
    }

    public Optional<Notificacion> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Notificacion actualizar(Long id, Notificacion datos) {
        Notificacion entidad = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Registro no encontrado"));
        datos.setId(entidad.getId());
        datos.setFechaCreacion(entidad.getFechaCreacion());
        return repository.save(datos);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}

