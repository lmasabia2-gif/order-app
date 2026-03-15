# Order App

A GitHub-ready Spring Boot starter for an order management service, based on the uploaded "Order Application - Repo-Ready Starter Structure" document.

## Stack

- Java 21
- Spring Boot 3.4
- Spring Web, Validation, Data JPA
- Spring Security + OAuth2 Resource Server (JWT)
- PostgreSQL
- Flyway
- Kafka
- Swagger / OpenAPI
- JUnit, Mockito, MockMvc, Testcontainers

## Project layout

```text
src/main/java/com/example/orderapp
├── config
├── common
├── customer
├── order
└── security
```

## Prerequisites

- Java 21
- Maven 3.9+
- Docker (recommended for local PostgreSQL / Kafka)

## Quick start

### 1. Start infrastructure

```bash
docker compose up -d
```

This starts:

- PostgreSQL on `localhost:5432`
- Kafka on `localhost:9092`
- Keycloak placeholder port mapping on `localhost:9000` is **not** included yet. Update `issuer-uri` or add your auth provider before testing secured JWT flows end-to-end.

### 2. Run the app

```bash
mvn spring-boot:run
```

### 3. Open useful endpoints

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Health: `http://localhost:8080/actuator/health`

## Default database settings

Configured in `src/main/resources/application.yml`:

- DB: `orderdb`
- User: `order_user`
- Password: `order_password`

## Seeded customers

Flyway seeds two customers:

- `11111111-1111-1111-1111-111111111111` - alice@example.com
- `22222222-2222-2222-2222-222222222222` - bob@example.com

## Example create order request

```json
{
  "customerId": "11111111-1111-1111-1111-111111111111",
  "currency": "EUR",
  "items": [
    {
      "productId": "SKU-1",
      "productName": "Keyboard",
      "quantity": 2,
      "unitPrice": 49.90
    }
  ]
}
```

## Notes

This starter is intentionally focused on the order service core. Good next steps:

- transactional outbox
- Kafka event publishing
- Testcontainers-based integration tests
- ownership-based access control
- inventory / payment / shipping clients

## GitHub readiness additions included here

- `.gitignore`
- `docker-compose.yml`
- `.github/workflows/ci.yml`

## Caveats

The source PDF references a couple of extra classes (`JacksonConfig`, `JwtAuthenticationConverterConfig`). Lightweight versions are included so the repository structure stays consistent, but role mapping and serialization policies will likely need to be tailored for your identity provider and API standards.
