package com.example.reservapro.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(unique = true) private String codigo;

    private String descripcion;

    private Long servicioId;

    private String tipoDescuento = "MONTO_FIJO";

    @DecimalMin("0.0") private BigDecimal valorDescuento = BigDecimal.ZERO;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    private Boolean activa = true;

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

