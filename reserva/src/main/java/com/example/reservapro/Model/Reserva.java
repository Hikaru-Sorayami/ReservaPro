package com.example.reservapro.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long usuarioId;

    @NotNull
    private Long servicioId;

    private Long disponibilidadId;

    @NotNull
    @FutureOrPresent
    private LocalDateTime fechaInicio;

    @NotNull
    @Future
    private LocalDateTime fechaFin;

    @Min(1)
    private Integer cantidadPersonas;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estadoReserva = EstadoReserva.PENDIENTE;

    @Enumerated(EnumType.STRING)
    private EstadoPago estadoPago = EstadoPago.PENDIENTE;

    @DecimalMin("0.0")
    private BigDecimal valorBase = BigDecimal.ZERO;

    @DecimalMin("0.0")
    private BigDecimal descuento = BigDecimal.ZERO;

    @DecimalMin("0.0")
    private BigDecimal valorFinal = BigDecimal.ZERO;

    private String codigoPromocion;
    private String observaciones;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    @PrePersist
    public void antesDeCrear() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
        calcularValorFinal();
    }

    @PreUpdate
    public void antesDeActualizar() {
        fechaActualizacion = LocalDateTime.now();
        calcularValorFinal();
    }

    public void calcularValorFinal() {
        BigDecimal base = valorBase == null ? BigDecimal.ZERO : valorBase;
        BigDecimal rebaja = descuento == null ? BigDecimal.ZERO : descuento;
        valorFinal = base.subtract(rebaja).max(BigDecimal.ZERO);
    }
}
