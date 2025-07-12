-- Activar extensión para generar UUIDs
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Crear tabla de solicitudes
CREATE TABLE IF NOT EXISTS solicitudes (
    id SERIAL  PRIMARY KEY,
    marca VARCHAR(100),
    tipo_solicitud VARCHAR(100),
    fecha_envio DATE,
    numero_contacto VARCHAR(20),
    nombre_contacto VARCHAR(100)
);

-- Crear tabla de contactos
CREATE TABLE IF NOT EXISTS contactos (
    id SERIAL  PRIMARY KEY,
    nombre_contacto VARCHAR(100),
    numero_contacto VARCHAR(20),
    solicitud_id INTEGER  REFERENCES solicitudes(id) ON DELETE CASCADE
);
