package com.example.reservapro.repository;

import com.example.reservapro.Model.EstadoPago;
import com.example.reservapro.Model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByUsuarioId(Long usuarioId);

    List<Pago> findByReservaId(Long reservaId);

    List<Pago> findByEstadoPago(EstadoPago estadoPago);

    Optional<Pago> findByReferenciaTransaccion(String referenciaTransaccion);

    boolean existsByReservaIdAndEstadoPago(Long reservaId, EstadoPago estadoPago);
}
