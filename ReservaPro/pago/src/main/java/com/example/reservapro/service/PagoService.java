package com.example.reservapro.service;

import com.example.reservapro.Model.EstadoPago;
import com.example.reservapro.Model.Pago;
import com.example.reservapro.repository.PagoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PagoService {
    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public Pago guardar(Pago pago) {
        if (pagoRepository.existsByReservaIdAndEstadoPago(pago.getReservaId(), EstadoPago.APROBADO)) {
            throw new IllegalArgumentException("La reserva ya tiene un pago aprobado");
        }

        pago.setEstadoPago(EstadoPago.PENDIENTE);
        asignarReferenciaSiNoExiste(pago);
        return pagoRepository.save(pago);
    }

    public List<Pago> listar() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> obtenerPorId(Long id) {
        return pagoRepository.findById(id);
    }

    public List<Pago> listarPorUsuario(Long usuarioId) {
        return pagoRepository.findByUsuarioId(usuarioId);
    }

    public List<Pago> listarPorReserva(Long reservaId) {
        return pagoRepository.findByReservaId(reservaId);
    }

    public List<Pago> listarPorEstado(EstadoPago estadoPago) {
        return pagoRepository.findByEstadoPago(estadoPago);
    }

    public Optional<Pago> obtenerPorReferencia(String referenciaTransaccion) {
        return pagoRepository.findByReferenciaTransaccion(referenciaTransaccion);
    }

    public Pago actualizar(Long id, Pago datosPago) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pago no encontrado"));

        pago.setReservaId(datosPago.getReservaId());
        pago.setUsuarioId(datosPago.getUsuarioId());
        pago.setMontoBase(datosPago.getMontoBase());
        pago.setDescuento(datosPago.getDescuento());
        pago.setMetodoPago(datosPago.getMetodoPago());
        pago.setCodigoPromocion(datosPago.getCodigoPromocion());
        pago.setDetalle(datosPago.getDetalle());

        return pagoRepository.save(pago);
    }

    public Pago aprobar(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pago no encontrado"));

        if (pagoRepository.existsByReservaIdAndEstadoPago(pago.getReservaId(), EstadoPago.APROBADO)) {
            throw new IllegalArgumentException("La reserva ya tiene un pago aprobado");
        }

        pago.setEstadoPago(EstadoPago.APROBADO);
        pago.setFechaPago(LocalDateTime.now());
        return pagoRepository.save(pago);
    }

    public Pago rechazar(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pago no encontrado"));
        pago.setEstadoPago(EstadoPago.RECHAZADO);
        return pagoRepository.save(pago);
    }

    public Pago anular(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pago no encontrado"));
        pago.setEstadoPago(EstadoPago.ANULADO);
        return pagoRepository.save(pago);
    }

    public Pago reembolsar(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pago no encontrado"));

        if (pago.getEstadoPago() != EstadoPago.APROBADO) {
            throw new IllegalArgumentException("Solo se pueden reembolsar pagos aprobados");
        }

        pago.setEstadoPago(EstadoPago.REEMBOLSADO);
        return pagoRepository.save(pago);
    }

    public void eliminar(Long id) {
        pagoRepository.deleteById(id);
    }

    private void asignarReferenciaSiNoExiste(Pago pago) {
        if (pago.getReferenciaTransaccion() == null || pago.getReferenciaTransaccion().isBlank()) {
            pago.setReferenciaTransaccion("PAGO-" + UUID.randomUUID());
        }
    }
}
