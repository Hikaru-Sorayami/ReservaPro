package com.example.reservapro;

import com.example.reservapro.Model.Opinion;
import com.example.reservapro.repository.OpinionRepository;
import com.example.reservapro.service.OpinionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OpinionServiceTest {
    @Mock
    private OpinionRepository repository;

    @InjectMocks
    private OpinionService service;

    @Test
    void guardarDebePersistirEntidad() {
        Opinion entidad = new Opinion();
        entidad.setUsuarioId(1L); entidad.setServicioId(1L); entidad.setCalificacion(5); entidad.setComentario("Excelente servicio");
        
        when(repository.save(entidad)).thenReturn(entidad);

        Opinion resultado = service.guardar(entidad);

        assertSame(entidad, resultado);
        verify(repository).save(entidad);
    }
}

