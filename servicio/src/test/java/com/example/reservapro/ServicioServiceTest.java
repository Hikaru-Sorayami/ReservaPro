package com.example.reservapro;

import com.example.reservapro.Model.Servicio;
import com.example.reservapro.repository.ServicioRepository;
import com.example.reservapro.service.ServicioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServicioServiceTest {
    @Mock
    private ServicioRepository repository;

    @InjectMocks
    private ServicioService service;

    @Test
    void guardarDebePersistirEntidad() {
        Servicio entidad = new Servicio();
        entidad.setNombre("Spa central"); entidad.setCapacidad(10);
        
        when(repository.save(entidad)).thenReturn(entidad);

        Servicio resultado = service.guardar(entidad);

        assertSame(entidad, resultado);
        verify(repository).save(entidad);
    }
}

