package com.example.reservapro;

import com.example.reservapro.Model.Reserva;
import com.example.reservapro.repository.ReservaRepository;
import com.example.reservapro.service.ReservaService;
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
class ReservaServiceTest {
    @Mock
    private ReservaRepository repository;

    @InjectMocks
    private ReservaService service;

    @Test
    void guardarDebePersistirEntidad() {
        Reserva entidad = new Reserva();
        entidad.setUsuarioId(1L); entidad.setServicioId(1L); entidad.setFechaInicio(LocalDateTime.now().plusDays(1)); entidad.setFechaFin(LocalDateTime.now().plusDays(1).plusHours(2)); entidad.setCantidadPersonas(2);
        when(repository.existeCruceHorario(entidad.getServicioId(), entidad.getFechaInicio(), entidad.getFechaFin(), null)).thenReturn(false);
        when(repository.save(entidad)).thenReturn(entidad);

        Reserva resultado = service.guardar(entidad);

        assertSame(entidad, resultado);
        verify(repository).save(entidad);
    }
}

