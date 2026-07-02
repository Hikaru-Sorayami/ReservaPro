package com.example.reservapro.repository;

import com.example.reservapro.Model.EstadoReserva;
import com.example.reservapro.Model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByUsuarioId(Long usuarioId);

    List<Reserva> findByServicioId(Long servicioId);

    List<Reserva> findByEstadoReserva(EstadoReserva estadoReserva);

    @Query("""
            select count(r) > 0
            from Reserva r
            where r.servicioId = :servicioId
              and r.estadoReserva <> com.example.reservapro.Model.EstadoReserva.CANCELADA
              and (:reservaId is null or r.id <> :reservaId)
              and r.fechaInicio < :fechaFin
              and r.fechaFin > :fechaInicio
            """)
    boolean existeCruceHorario(
            @Param("servicioId") Long servicioId,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin,
            @Param("reservaId") Long reservaId
    );
}
