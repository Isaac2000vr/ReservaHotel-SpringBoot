# 🏨 ReservaHotel - Spring Boot MVC con Thymeleaf (Unidad 2)

Aplicación web desarrollada con **Spring Boot**, **Spring MVC**, **Spring Data JPA** y **Thymeleaf** para la gestión de usuarios y reservas de hotel (Ejercicio N.º 31).

---

## 🛠️ Enfoque Tecnológico
- **Framework Principal:** Spring Boot 3.x
- **Persistencia:** Spring Data JPA + Hibernate sobre MySQL 8.4 (Clever Cloud)
- **Vistas:** Plantillas HTML renderizadas con Thymeleaf
- **Control de Versiones:** Git / GitHub

---

## 🗄️ Esquema de Base de Datos y Credenciales

Las variables de entorno requeridas son:
- `DB_HOST`: Host de MySQL
- `DB_PORT`: Puerto de conexión (3306)
- `DB_USER`: Usuario de la BD
- `DB_PASSWORD`: Contraseña de la BD
- `DB_NAME`: Nombre de la base de datos

### Credenciales por Defecto (Clever Cloud)
- **Host:** `bpljmrsrmouuqribgl2y-mysql.services.clever-cloud.com`
- **DB Name:** `bpljmrsrmouuqribgl2y`

---

## 🔑 Autenticación de Prueba
- **Admin:** `admin@reservahotel.com` / `admin123`
- **Empleado 1:** `laura@reservahotel.com` / `empleado123`
- **Empleado 2:** `carlos@reservahotel.com` / `empleado456`

---

## 🚀 Instrucciones de Ejecución Local
```bash
./mvnw spring-boot:run
```
Acceder en el navegador a: `http://localhost:8080`