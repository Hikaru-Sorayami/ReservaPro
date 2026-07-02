package com.example.reservapro.service;

import com.example.reservapro.Model.Reporte;
import com.example.reservapro.repository.ReporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {
    private final ReporteRepository repository;

    public ReporteService(ReporteRepository repository) {
        this.repository = repository;
    }

    public Reporte guardar(Reporte entidad) {
        return repository.save(entidad);
    }

    public List<Reporte> listar() {
        return repository.findAll();
    }

    public Optional<Reporte> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Reporte actualizar(Long id, Reporte datos) {
        Reporte entidad = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Registro no encontrado"));
        datos.setId(entidad.getId());
        datos.setFechaCreacion(entidad.getFechaCreacion());
        return repository.save(datos);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}

