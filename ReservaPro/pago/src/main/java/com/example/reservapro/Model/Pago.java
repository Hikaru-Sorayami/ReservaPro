package com.example.reservapro.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long reservaId;

    @NotNull
    private Long usuarioId;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal montoBase = BigDecimal.ZERO;

    @DecimalMin("0.0")
    private BigDecimal descuento = BigDecimal.ZERO;

    @DecimalMin("0.0")
    private BigDecimal montoFinal = BigDecimal.ZERO;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @Enumerated(EnumType.STRING)
    private EstadoPago estadoPago = EstadoPago.PENDIENTE;

    private String codigoPromocion;
    private String referenciaTransaccion;
    private String detalle;
    private LocalDateTime fechaPago;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    @PrePersist
    public void antesDeCrear() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
        calcularMontoFinal();
    }

    @PreUpdate
    public void antesDeActualizar() {
        fechaActualizacion = LocalDateTime.now();
        calcularMontoFinal();
    }

    public void calcularMontoFinal() {
        BigDecimal base = montoBase == null ? BigDecimal.ZERO : montoBase;
        BigDecimal rebaja = descuento == null ? BigDecimal.ZERO : descuento;
        montoFinal = base.subtract(rebaja).max(BigDecimal.ZERO);
    }
}
