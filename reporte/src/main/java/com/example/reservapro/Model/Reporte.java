package com.example.reservapro.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String tipo;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    @Min(0) private Integer totalReservas = 0;

    @Min(0) private Integer totalPagos = 0;

    @DecimalMin("0.0") private BigDecimal ingresoTotal = BigDecimal.ZERO;

    private Long generadoPor;

    private String formato = "PDF";

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    @PrePersist
    public void antesDeCrear() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    public void antesDeActualizar() {
        fechaActualizacion = LocalDateTime.now();
    }
}

