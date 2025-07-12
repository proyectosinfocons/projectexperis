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

## 📌 Endpoints - SolicitudController
Se va a usar los siguientes enpoints:

Base path: `/api/solicitudes`

| Método | Endpoint                  | Descripción                                      | Request Body             | Response                       |
|--------|---------------------------|--------------------------------------------------|--------------------------|--------------------------------|
| POST   | `/api/solicitudes`        | Crea una nueva solicitud (y sus contactos)       | ✅ `SolicitudRequest`     | `Mono<ResponseEntity<Solicitud>>` |
| GET    | `/api/solicitudes`        | Lista todas las solicitudes registradas          | ❌ No                    | `Flux<Solicitud>`             |
| GET    | `/api/solicitudes/{id}`   | Obtiene una solicitud por su ID                  | ❌ No                    | `Mono<ResponseEntity<Solicitud>>` |
| GET    | `/api/solicitudes/export` | Exporta todas las solicitudes en formato CSV     | ❌ No                    | `Mono<ResponseEntity<String>>` con archivo CSV |

---

### 📥 Ejemplo JSON para POST `/api/solicitudes`

```json
{
   "marca": "Samsung",
   "tipoSolicitud": "Garantía",
   "fechaEnvio": "2025-07-12",
   "numeroContacto": "987654321",
   "nombreContacto": "Carlos Pérez",
   "contactos": [
      {
         "nombreContacto": "Ana Martínez",
         "numeroContacto": "912345678"
      },
      {
         "nombreContacto": "Luis Torres",
         "numeroContacto": "998877665"
      }
   ]
}
```

# 📡 CURL - Endpoints de la API (Solicitudes y Contactos)

Base URL: `http://localhost:8080`  
Formato de datos: `application/json`

---

## 📬 1. Crear una nueva solicitud

**POST `/api/solicitudes`**

```bash
curl -X POST http://localhost:8080/api/solicitudes \
  -H "Content-Type: application/json" \
  -d '{
    "marca": "Samsung",
    "tipoSolicitud": "Garantía",
    "fechaEnvio": "2025-07-12",
    "numeroContacto": "987654321",
    "nombreContacto": "Carlos Pérez",
    "contactos": [
      {
        "nombreContacto": "Ana Martínez",
        "numeroContacto": "912345678"
      },
      {
        "nombreContacto": "Luis Torres",
        "numeroContacto": "998877665"
      }
    ]
  }'
```

---

## 📥 2. Listar todas las solicitudes

**GET `/api/solicitudes`**

```bash
curl http://localhost:8080/api/solicitudes
```

---

## 📄 3. Obtener una solicitud por ID

**GET `/api/solicitudes/{id}`**
   
{id}: el id es de la solicitud
```bash
curl http://localhost:8080/api/solicitudes/1
```

> 🔁 Reemplaza `1` con el ID real.

---

## 📤 4. Exportar solicitudes en CSV

**GET `/api/solicitudes/export`**

```bash
curl -X GET http://localhost:8080/api/solicitudes/export -o solicitudes.csv
```

> 📁 Descarga el archivo `solicitudes.csv`.

Nota:

- Importar esto en el postman, para realizar pruebas en el BackEnd


## 🛠️ Configuración de base de datos y ejecucion de la aplicación

1. Descargar el proyecto con un git clone:
```bash
git clone https://github.com/proyectosinfocons/projectexperis.git
```
2. Crea una base de datos llamada:
   En pgAdmin:
   Crea la base de datos manualmente con clic derecho > Create > Database
3. Se ejecuta la aplicación, al hacer click en la opción que esta marcado en el cuadro rojo como se muestra en la imagen.
![muestraEjecucion.png](src%2Fmain%2Fresources%2FmuestraEjecucion.png)

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
```
3. Luego descargar el proyecto con un git clone:
```bash
git clone https://github.com/proyectosinfocons/solicitudes-react.git
```
4. Y abrir el proyecto en visual studio y abrir la terminal.
5. Ejecutar el siguiente comando:
```bash   
npm install
```
6. Y luego ejecutar el siguiente comando:
```bash 
npm install react-router-dom
```
6. Y luego ejecutar el siguiente comando para ejecutar la aplicacion:
```bash 
npm run dev
```