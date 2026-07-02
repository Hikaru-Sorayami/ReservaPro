package com.example.reservapro.service;

import com.example.reservapro.Model.EstadoPago;
import com.example.reservapro.Model.EstadoReserva;
import com.example.reservapro.Model.Reserva;
import com.example.reservapro.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva guardar(Reserva reserva) {
        validarReserva(reserva, null);
        reserva.setEstadoReserva(EstadoReserva.PENDIENTE);
        reserva.setEstadoPago(EstadoPago.PENDIENTE);
        return reservaRepository.save(reserva);
    }

    public List<Reserva> listar() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> obtenerPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public List<Reserva> listarPorUsuario(Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    public List<Reserva> listarPorServicio(Long servicioId) {
        return reservaRepository.findByServicioId(servicioId);
    }

    public List<Reserva> listarPorEstado(EstadoReserva estadoReserva) {
        return reservaRepository.findByEstadoReserva(estadoReserva);
    }

    public Reserva modificar(Long id, Reserva datosReserva) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));

        validarReserva(datosReserva, id);
        reserva.setUsuarioId(datosReserva.getUsuarioId());
        reserva.setServicioId(datosReserva.getServicioId());
        reserva.setDisponibilidadId(datosReserva.getDisponibilidadId());
        reserva.setFechaInicio(datosReserva.getFechaInicio());
        reserva.setFechaFin(datosReserva.getFechaFin());
        reserva.setCantidadPersonas(datosReserva.getCantidadPersonas());
        reserva.setValorBase(datosReserva.getValorBase());
        reserva.setDescuento(datosReserva.getDescuento());
        reserva.setCodigoPromocion(datosReserva.getCodigoPromocion());
        reserva.setObservaciones(datosReserva.getObservaciones());
        reserva.setEstadoReserva(EstadoReserva.MODIFICADA);

        return reservaRepository.save(reserva);
    }

    public Reserva confirmar(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));
        reserva.setEstadoReserva(EstadoReserva.CONFIRMADA);
        return reservaRepository.save(reserva);
    }

    public Reserva cancelar(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));
        reserva.setEstadoReserva(EstadoReserva.CANCELADA);
        if (reserva.getEstadoPago() == EstadoPago.PAGADO) {
            reserva.setEstadoPago(EstadoPago.REEMBOLSADO);
        }
        return reservaRepository.save(reserva);
    }

    public Reserva actualizarPago(Long id, EstadoPago estadoPago) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));
        reserva.setEstadoPago(estadoPago);
        return reservaRepository.save(reserva);
    }

    public void eliminar(Long id) {
        reservaRepository.deleteById(id);
    }

    private void validarReserva(Reserva reserva, Long reservaId) {
        if (reserva.getFechaInicio() == null || reserva.getFechaFin() == null) {
            throw new IllegalArgumentException("La reserva debe tener fecha de inicio y fecha de fin");
        }

        if (!reserva.getFechaFin().isAfter(reserva.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la fecha de inicio");
        }

        boolean existeCruce = reservaRepository.existeCruceHorario(
                reserva.getServicioId(),
                reserva.getFechaInicio(),
                reserva.getFechaFin(),
                reservaId
        );

        if (existeCruce) {
            throw new IllegalArgumentException("El servicio ya tiene una reserva en ese horario");
        }
    }
}
