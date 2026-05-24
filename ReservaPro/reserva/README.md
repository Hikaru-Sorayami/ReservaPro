# Microservicio de Reservas - ReservaPro

Microservicio Spring Boot basado en la estructura del `usuario.zip`: controller, model, repository, service y configuracion para MySQL.

## Base de datos

Crear una base de datos MySQL llamada:

```sql
CREATE DATABASE `ms-reserva`;
```

La conexion esta en `src/main/resources/application.properties`:

```properties
server.port=8082
spring.datasource.url=jdbc:mysql://localhost:3306/ms-reserva
spring.datasource.username=root
spring.datasource.password=
```

## Endpoints principales

- `POST /reservas`: crea una reserva.
- `GET /reservas`: lista todas las reservas.
- `GET /reservas/{id}`: obtiene una reserva por id.
- `GET /reservas/usuario/{usuarioId}`: lista reservas por usuario.
- `GET /reservas/servicio/{servicioId}`: lista reservas por servicio.
- `GET /reservas/estado/{estadoReserva}`: filtra por `PENDIENTE`, `CONFIRMADA`, `MODIFICADA` o `CANCELADA`.
- `PUT /reservas/{id}`: modifica una reserva.
- `PATCH /reservas/{id}/confirmar`: confirma la reserva.
- `PATCH /reservas/{id}/cancelar`: cancela la reserva.
- `PATCH /reservas/{id}/pago?estadoPago=PAGADO`: cambia estado de pago.
- `DELETE /reservas/{id}`: elimina una reserva.

## Ejemplo de creacion

```json
{
  "usuarioId": 1,
  "servicioId": 2,
  "disponibilidadId": 5,
  "fechaInicio": "2026-06-01T10:00:00",
  "fechaFin": "2026-06-01T12:00:00",
  "cantidadPersonas": 2,
  "valorBase": 50000,
  "descuento": 5000,
  "codigoPromocion": "PROMO10",
  "observaciones": "Reserva para spa"
}
```

El servicio valida que la fecha de fin sea posterior a la de inicio y evita reservas cruzadas para el mismo servicio mientras la reserva no este cancelada.
