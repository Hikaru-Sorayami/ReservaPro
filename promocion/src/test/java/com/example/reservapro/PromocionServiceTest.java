package com.example.reservapro;

import com.example.reservapro.Model.Promocion;
import com.example.reservapro.repository.PromocionRepository;
import com.example.reservapro.service.PromocionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PromocionServiceTest {
    @Mock
    private PromocionRepository repository;

    @InjectMocks
    private PromocionService service;

    @Test
    void guardarDebePersistirEntidad() {
        Promocion entidad = new Promocion();
        entidad.setCodigo("PROMO10"); entidad.setDescripcion("Descuento de prueba");
        
        when(repository.save(entidad)).thenReturn(entidad);

        Promocion resultado = service.guardar(entidad);

        assertSame(entidad, resultado);
        verify(repository).save(entidad);
    }
}

