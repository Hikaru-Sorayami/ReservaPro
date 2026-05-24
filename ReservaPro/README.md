# ReservaPro

Proyecto organizado con tres microservicios Spring Boot para el caso de estudio ReservaPro.

## Microservicios

- `usuario`: gestion de usuarios, roles, estados y login. Puerto `8081`.
- `reserva`: gestion de reservas, modificaciones, cancelaciones y estado de pago. Puerto `8082`.
- `pago`: gestion de pagos, aprobacion, rechazo, anulacion y reembolso. Puerto `8083`.

## Bases de datos MySQL

Antes de ejecutar, crear estas bases:

```sql
CREATE DATABASE `ms-usuario`;
CREATE DATABASE `ms-reserva`;
CREATE DATABASE `ms-pago`;
```

Cada microservicio mantiene su propio `pom.xml`, su propio `application.properties` y sus capas:

- `controller`
- `Model`
- `repository`
- `service`
- `exception`

## Flujo de prueba

1. Levantar `usuario` y crear un usuario en `POST http://localhost:8081/usuarios`.
2. Levantar `reserva` y crear una reserva en `POST http://localhost:8082/reservas`.
3. Levantar `pago` y crear un pago en `POST http://localhost:8083/pagos`.
4. Aprobar el pago con `PATCH http://localhost:8083/pagos/{id}/aprobar`.
5. Confirmar la reserva con `PATCH http://localhost:8082/reservas/{id}/confirmar`.
