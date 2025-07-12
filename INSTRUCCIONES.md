# 🧠 Backend - Proyecto de Solicitudes (Spring Boot + R2DBC + PostgreSQL)

Este backend implementa una API reactiva para registrar, consultar y exportar solicitudes y sus contactos asociados.

---

## ⚙️ Tecnologías usadas

- Java 17
- Spring Boot 3.5.x
- Spring WebFlux (reactivo)
- Spring Data R2DBC
- PostgreSQL
- Lombok
- Gradle
- CORS habilitado para frontend React

---

## 📦 Requisitos previos

- Java 17+
- PostgreSQL (conectado a la base `bd_experis`)
- Gradle (o usar `./gradlew`)
- PgAdmin 4 (opcional para gestionar la DB)
- IDE recomendado: IntelliJ IDEA o VS Code

---

## 🛠️ Configuración de base de datos

1. Crea una base de datos llamada:
   En pgAdmin:
   Crea la base de datos manualmente con clic derecho > Create > Database

2. Al ejecutar la aplicacion se ejecuta el script de forma automatica.
```sql  
CREATE TABLE IF NOT EXISTS solicitudes (
   id SERIAL PRIMARY KEY,
   marca VARCHAR(100),
   tipo_solicitud VARCHAR(100),
   fecha_envio DATE,
   numero_contacto VARCHAR(20),
   nombre_contacto VARCHAR(100)
   );

CREATE TABLE IF NOT EXISTS contactos (
id SERIAL PRIMARY KEY,
nombre_contacto VARCHAR(100),
numero_contacto VARCHAR(20),
solicitud_id INTEGER REFERENCES solicitudes(id) ON DELETE CASCADE
);
