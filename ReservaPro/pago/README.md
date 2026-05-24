# Microservicio de Pagos - ReservaPro

Microservicio Spring Boot para registrar, aprobar, rechazar, anular y reembolsar pagos asociados a reservas.

## Base de datos

```sql
CREATE DATABASE `ms-pago`;
```

## Endpoints

- `POST /pagos`: crea pago pendiente.
- `GET /pagos`: lista pagos.
- `GET /pagos/{id}`: obtiene pago por id.
- `GET /pagos/usuario/{usuarioId}`: lista pagos por usuario.
- `GET /pagos/reserva/{reservaId}`: lista pagos por reserva.
- `GET /pagos/estado/{estadoPago}`: filtra por `PENDIENTE`, `APROBADO`, `RECHAZADO`, `ANULADO` o `REEMBOLSADO`.
- `GET /pagos/referencia/{referenciaTransaccion}`: busca por referencia.
- `PUT /pagos/{id}`: actualiza datos del pago.
- `PATCH /pagos/{id}/aprobar`: aprueba pago.
- `PATCH /pagos/{id}/rechazar`: rechaza pago.
- `PATCH /pagos/{id}/anular`: anula pago.
- `PATCH /pagos/{id}/reembolsar`: reembolsa pago aprobado.
- `DELETE /pagos/{id}`: elimina pago.

## Ejemplo

```json
{
  "reservaId": 1,
  "usuarioId": 1,
  "montoBase": 50000,
  "descuento": 5000,
  "metodoPago": "WEBPAY",
  "codigoPromocion": "PROMO10",
  "detalle": "Pago de reserva de spa"
}
```
