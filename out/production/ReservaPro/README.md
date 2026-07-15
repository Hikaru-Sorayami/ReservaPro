OBSERVACIONES:

1. Implementar Eureka (guía esta en AVA)
2. Implementar OpenFeign (comunicación entre microservicios)
3. Abordar 3 microservicios primero (usuarios, reserva, pago)
4. Corregir docker-compose con solamente esos 3 microservicios.
5. Usar Swagger





# ReservaPro

Proyecto de arquitectura de microservicios para el caso ReservaPro.

Incluye microservicios independientes con patrón CSR:

- `controller`
- `service`
- `repository`
- `Model`
- `exception`

Además incluye:

- Swagger/OpenAPI en cada microservicio.
- Archivos `application.yml`.
- Pruebas unitarias base con JUnit y Mockito.
- API Gateway equivalente para centralizar rutas.
- Dockerfile por microservicio.
- `docker-compose.yml` para levantar MySQL, microservicios y Gateway.

## Microservicios

| Microservicio | Puerto | Base de datos | Endpoint principal | Swagger |
| --- | ---: | --- | --- | --- |
| gateway | 8080 | No usa BD | `/api/...` | `http://localhost:8080/swagger-ui.html` |
| usuario | 8081 | ms-usuario | `/usuarios` | `http://localhost:8081/swagger-ui.html` |
| reserva | 8082 | ms-reserva | `/reservas` | `http://localhost:8082/swagger-ui.html` |
| pago | 8083 | ms-pago | `/pagos` | `http://localhost:8083/swagger-ui.html` |
| servicio | 8084 | ms-servicio | `/servicios` | `http://localhost:8084/swagger-ui.html` |
| disponibilidad | 8085 | ms-disponibilidad | `/disponibilidades` | `http://localhost:8085/swagger-ui.html` |
| cancelacion | 8086 | ms-cancelacion | `/cancelaciones` | `http://localhost:8086/swagger-ui.html` |
| promocion | 8087 | ms-promocion | `/promociones` | `http://localhost:8087/swagger-ui.html` |
| notificacion | 8088 | ms-notificacion | `/notificaciones` | `http://localhost:8088/swagger-ui.html` |
| opinion | 8089 | ms-opinion | `/opiniones` | `http://localhost:8089/swagger-ui.html` |
| reporte | 8090 | ms-reporte | `/reportes` | `http://localhost:8090/swagger-ui.html` |
| historial | 8091 | ms-historial | `/historial-reservas` | `http://localhost:8091/swagger-ui.html` |

## Bases de datos MySQL

Ejecutar en phpMyAdmin o MySQL Workbench:

```sql
CREATE DATABASE IF NOT EXISTS `ms-usuario`;
CREATE DATABASE IF NOT EXISTS `ms-reserva`;
CREATE DATABASE IF NOT EXISTS `ms-pago`;
CREATE DATABASE IF NOT EXISTS `ms-servicio`;
CREATE DATABASE IF NOT EXISTS `ms-disponibilidad`;
CREATE DATABASE IF NOT EXISTS `ms-cancelacion`;
CREATE DATABASE IF NOT EXISTS `ms-promocion`;
CREATE DATABASE IF NOT EXISTS `ms-notificacion`;
CREATE DATABASE IF NOT EXISTS `ms-opinion`;
CREATE DATABASE IF NOT EXISTS `ms-reporte`;
CREATE DATABASE IF NOT EXISTS `ms-historial`;
```

Tambien puedes usar el archivo:

```text
crear-bases.sql
```

Las tablas se crean solas al arrancar cada microservicio por:

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: update
```

## Ejecucion local desde IDE

1. Abrir la carpeta `ReservaPro`.
2. Abrir cada microservicio como proyecto Maven.
3. Confirmar que MySQL este encendido en `localhost:3306`.
4. Crear las bases de datos.
5. Ejecutar primero los microservicios que se quieran probar.
6. Ejecutar `gateway` si se quiere consumir todo desde el puerto `8080`.

Cada servicio responde en `/`:

```text
http://localhost:8081/
http://localhost:8082/
http://localhost:8083/
```

## Gateway

El Gateway corre en:

```text
http://localhost:8080
```

Rutas principales:

```text
GET http://localhost:8080/api/usuarios
GET http://localhost:8080/api/reservas
GET http://localhost:8080/api/pagos
GET http://localhost:8080/api/servicios
GET http://localhost:8080/api/disponibilidades
GET http://localhost:8080/api/cancelaciones
GET http://localhost:8080/api/promociones
GET http://localhost:8080/api/notificaciones
GET http://localhost:8080/api/opiniones
GET http://localhost:8080/api/reportes
GET http://localhost:8080/api/historial-reservas
```

## Docker

Con Docker Desktop encendido:

```bash
docker compose up --build
```

Esto levanta:

- MySQL
- Los 11 microservicios
- Gateway

## Pruebas

Cada microservicio tiene pruebas unitarias base en `src/test/java`.

Ejecutar en cada carpeta:

```bash
mvn test
```

## Flujo sugerido para probar

1. Crear usuario en `POST http://localhost:8080/api/usuarios`.
2. Crear servicio en `POST http://localhost:8080/api/servicios`.
3. Crear disponibilidad en `POST http://localhost:8080/api/disponibilidades`.
4. Crear reserva en `POST http://localhost:8080/api/reservas`.
5. Crear pago en `POST http://localhost:8080/api/pagos`.
6. Aprobar pago en `PATCH http://localhost:8080/api/pagos/{id}/aprobar`.
7. Confirmar reserva en `PATCH http://localhost:8080/api/reservas/{id}/confirmar`.

## Requisitos externos

Para tenerlo listo fuera del codigo necesitas:

- JDK 17 o superior.
- Maven instalado y agregado al PATH.
- MySQL o XAMPP con MySQL encendido.
- Bases de datos creadas con `crear-bases.sql`.
- Postman o Swagger para probar endpoints.
- Docker Desktop si quieres usar `docker-compose.yml`.
- Repositorio GitHub publico o compartido con el docente.
- Tablero Trello u otra evidencia de gestion del equipo.
