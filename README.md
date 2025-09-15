# Base Template

A base template for Spring Boot 3 projects following hexagonal architecture principles.

## Project Structure

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           ├── Application.java
│   │           ├── adapter
│   │           │   ├── in
│   │           │   │   └── web
│   │           │   │       ├── HelloController.java
│   │           │   │       └── SwaggerConfig.java
│   │           │   └── out
│   │           │       └── persistence
│   │           │           ├── HelloEntity.java
│   │           │           └── HelloRepository.java
│   │           └── domain
│   │               └── Hello.java
│   └── resources
│       ├── application-dev.yml
│       ├── application-prod.yml
│       └── db
│           └── migration
│               └── V1__Initial_setup.sql

```

## Developer Profile

To clean, package, and re-create the database in the development environment, use the following command:

```
mvn clean package -DsqlMode=always -Dprofile=dev
```

To enable and disable integration tests
```
mvn test -Dtestcontainers=enabled
mvn test -Dtestcontainers=disabled
´´´

## Production Profile

To clean, package, and re-create the database in the production environment, use the following command:

```
mvn clean package -DsqlMode=always -Dprofile=prod
```

mvn surefire-report:report && start target/reports/surefire.html

## Swagger Page

The Swagger UI can be accessed at the following URL:

```
http://localhost:8080/swagger-ui/index.html
```
