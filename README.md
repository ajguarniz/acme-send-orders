# ACME Send Orders

API REST que recibe pedidos en formato **JSON**, los transforma a **XML (SOAP)**, los envía al sistema legacy y devuelve la respuesta en **JSON**.

---

## Tecnologías

- Java 21
- Spring Boot 4.0.5
- WebClient (Reactive)
- Resilience4j (Retry + Circuit Breaker)
- Java Records (DTOs)
- Swagger / OpenAPI 3
- Docker + Docker Compose
- JUnit 5 + Mockito

---

## ✨ Características

- Transformación JSON ↔ XML (SOAP)
- Protección con **Retry** y **Circuit Breaker**
- Manejo centralizado de excepciones
- DTOs implementados con **Java Records**
- Logging estructurado
- Soporte completo para Docker
- Tests unitarios e integrales
- Documentación interactiva con Swagger

---

## 📋 Requisitos Previos

- **Java 21**
- **Maven 3.9+**
- **Docker** y **Docker Compose**

---

## ▶️ Ejecución Local

```bash
# Opción 1: Ejecutar directamente con Maven
./mvnw spring-boot:run

# Opción 2: Compilar y ejecutar el JAR
./mvnw clean package
java -jar target/acme-send-orders-0.0.1-SNAPSHOT.jar

```

## 📡 API
Endpoint principal

```bash
POST /api/orders/send
Content-Type: application/json
```

## Ejemplo de Body:

```bash
{
  "numPedido": "75630275",
  "cantidadPedido": "1",
  "codigoEAN": "00110000765191002104587",
  "nombreProducto": "Armario INVAL",
  "numDocumento": "1113987400",
  "direccion": "CR 72B 45 12 APT 301"
}
```

## 📖 Documentación Swagger

```bash
Accede a la documentación interactiva en:
→ http://localhost:8080/swagger-ui.html
```

## 🧪 Tests

```bash
# Ejecutar todos los tests
./mvnw test

# Ejecutar solo los tests del servicio
./mvnw test -Dtest=OrderServiceImplTest
```

## 🐳 Docker

```bash
# Construir imagen
docker build -t acme-send-orders .

# Ejecutar contenedor
docker run -p 8080:8080 acme-send-orders
```

## 🐙 Docker Compose

```bash
# Levantar el servicio
docker-compose up --build

# Levantar en segundo plano
docker-compose up -d --build

# Ver logs
docker-compose logs -f

# Detener
docker-compose down
```

## Estructura del Proyecto

```bash
acme-send-orders/
├── src/
│   ├── main/
│   │   ├── java/com/acme/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── client/
│   │   │   ├── config/
│   │   │   ├── exception/
│   │   │   ├── model/xml/
│   │   │   ├── service/
│   │   │   └── util/
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── application-dev.yml
│   │       └── application-prod.yml
│   └── test/java/com/acme/service/impl/
│       └── OrderServiceImplTest.java
├── Dockerfile
├── docker-compose.yml
├── README.md
└── pom.xml
```
