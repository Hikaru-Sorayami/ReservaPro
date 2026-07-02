package com.example.reservapro.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull private Long usuarioId;

    private Long reservaId;

    private String canal = "EMAIL";

    private String tipo;

    @NotBlank private String mensaje;

    private String estado = "PENDIENTE";

    private LocalDateTime fechaProgramada;

    private LocalDateTime fechaEnvio;

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

