# order-app

Spring Boot sample microservice with:
- feature-first package structure
- thin controller
- service layer
- mapper
- global exception handling
- H2 in-memory database
- sample data
- ready-to-run endpoints

## Import into Eclipse
File -> Import -> Existing Maven Projects

## Run
mvn spring-boot:run

## Endpoints
- GET /orders
- GET /orders/{id}
- GET /orders/customer/{customerId}
- POST /orders
- DELETE /orders/{id}

## Swagger
- http://localhost:8080/swagger-ui.html

## H2 console
- http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:orderdb
- User: sa
- Password: (empty)

## Sample POST body
{
  "customerId": 999,
  "amount": 49.90
}
