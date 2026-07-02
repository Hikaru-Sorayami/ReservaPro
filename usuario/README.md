# Microservicio de Usuarios - ReservaPro

Microservicio Spring Boot basado en `usuario.zip`, corregido para que las dependencias se inyecten por constructor y el servicio funcione con MySQL.

## Base de datos

```sql
CREATE DATABASE `ms-usuario`;
```

## Endpoints

- `POST /usuarios`: crea usuario.
- `GET /usuarios`: lista usuarios.
- `GET /usuarios/{id}`: obtiene usuario por id.
- `GET /usuarios/email/{email}`: obtiene usuario por email.
- `GET /usuarios/rol/{rol}`: filtra por `ADMINISTRADOR`, `CLIENTE` u `OPERADOR_SERVICIO`.
- `GET /usuarios/estado/{estado}`: filtra por `ACTIVO`, `INACTIVO` o `BLOQUEADO`.
- `PUT /usuarios/{id}`: actualiza usuario.
- `PATCH /usuarios/{id}/estado?estado=INACTIVO`: cambia estado.
- `POST /usuarios/login`: valida email, password y estado activo.
- `DELETE /usuarios/{id}`: elimina usuario.

## Ejemplo

```json
{
  "nombre": "Camila",
  "apellido": "Rojas",
  "email": "camila@reservapro.cl",
  "password": "123456",
  "telefono": "+56911112222",
  "direccion": "Santiago",
  "rol": "CLIENTE",
  "estado": "ACTIVO"
}
```
