package com.example.reservapro;

import com.example.reservapro.Model.Pago;
import com.example.reservapro.repository.PagoRepository;
import com.example.reservapro.service.PagoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import com.example.reservapro.Model.MetodoPago;
import com.example.reservapro.Model.EstadoPago;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagoServiceTest {
    @Mock
    private PagoRepository repository;

    @InjectMocks
    private PagoService service;

    @Test
    void guardarDebePersistirEntidad() {
        Pago entidad = new Pago();
        entidad.setReservaId(1L); entidad.setUsuarioId(1L); entidad.setMontoBase(BigDecimal.valueOf(50000)); entidad.setMetodoPago(MetodoPago.WEBPAY);
        when(repository.existsByReservaIdAndEstadoPago(entidad.getReservaId(), EstadoPago.APROBADO)).thenReturn(false);
        when(repository.save(entidad)).thenReturn(entidad);

        Pago resultado = service.guardar(entidad);

        assertSame(entidad, resultado);
        verify(repository).save(entidad);
    }
}


