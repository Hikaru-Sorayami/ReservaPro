package com.example.reservapro;

import com.example.reservapro.Model.HistorialReserva;
import com.example.reservapro.repository.HistorialReservaRepository;
import com.example.reservapro.service.HistorialReservaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistorialReservaServiceTest {
    @Mock
    private HistorialReservaRepository repository;

    @InjectMocks
    private HistorialReservaService service;

    @Test
    void guardarDebePersistirEntidad() {
        HistorialReserva entidad = new HistorialReserva();
        entidad.setReservaId(1L); entidad.setUsuarioId(1L); entidad.setAccion("CREACION");
        
        when(repository.save(entidad)).thenReturn(entidad);

        HistorialReserva resultado = service.guardar(entidad);

        assertSame(entidad, resultado);
        verify(repository).save(entidad);
    }
}

