package com.example.reservapro;

import com.example.reservapro.Model.Notificacion;
import com.example.reservapro.repository.NotificacionRepository;
import com.example.reservapro.service.NotificacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificacionServiceTest {
    @Mock
    private NotificacionRepository repository;

    @InjectMocks
    private NotificacionService service;

    @Test
    void guardarDebePersistirEntidad() {
        Notificacion entidad = new Notificacion();
        entidad.setUsuarioId(1L); entidad.setMensaje("Reserva confirmada");
        
        when(repository.save(entidad)).thenReturn(entidad);

        Notificacion resultado = service.guardar(entidad);

        assertSame(entidad, resultado);
        verify(repository).save(entidad);
    }
}

