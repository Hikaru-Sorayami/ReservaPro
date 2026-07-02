package com.example.reservapro;

import com.example.reservapro.Model.Disponibilidad;
import com.example.reservapro.repository.DisponibilidadRepository;
import com.example.reservapro.service.DisponibilidadService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DisponibilidadServiceTest {
    @Mock
    private DisponibilidadRepository repository;

    @InjectMocks
    private DisponibilidadService service;

    @Test
    void guardarDebePersistirEntidad() {
        Disponibilidad entidad = new Disponibilidad();
        entidad.setServicioId(1L); entidad.setFechaInicio(LocalDateTime.now().plusDays(1)); entidad.setFechaFin(LocalDateTime.now().plusDays(1).plusHours(2)); entidad.setCuposDisponibles(5);
        
        when(repository.save(entidad)).thenReturn(entidad);

        Disponibilidad resultado = service.guardar(entidad);

        assertSame(entidad, resultado);
        verify(repository).save(entidad);
    }
}

