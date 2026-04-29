package com.example.ms_reserva.Reservas;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;
    private String servicio;
    private String fecha;
    private String estado;
}