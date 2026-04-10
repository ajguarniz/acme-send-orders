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
- **Docker** y **Docker Compose** (opcional)

---

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

