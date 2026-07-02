package com.example.reservapro;

import com.example.reservapro.Model.Cancelacion;
import com.example.reservapro.repository.CancelacionRepository;
import com.example.reservapro.service.CancelacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CancelacionServiceTest {
    @Mock
    private CancelacionRepository repository;

    @InjectMocks
    private CancelacionService service;

    @Test
    void guardarDebePersistirEntidad() {
        Cancelacion entidad = new Cancelacion();
        entidad.setReservaId(1L); entidad.setUsuarioId(1L); entidad.setMotivo("Cambio de planes");
        
        when(repository.save(entidad)).thenReturn(entidad);

        Cancelacion resultado = service.guardar(entidad);

        assertSame(entidad, resultado);
        verify(repository).save(entidad);
    }
}

