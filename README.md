# Spring Boot AWS

Spring Boot application deployed on AWS.

## Build with Maven

- Navigate to the server folder:
```bash
cd application/server
```

- Build the server:
```bash
mvn clean package
```

> **Note:**
> You can use the `-DskipTests` option to skip the tests.

## Run with Docker

- Navigate to the Docker folder:
```bash
cd infrastructure/docker
```

- Run the application:
```bash
docker-compose up
```

> **Note:**
> You can use the `--build` option to force the images to be built before starting the application.

> **Note:**
> You can use the `-d` or `--detach` option to run the application in the background.

- The server will be available at http://localhost:8080
- The server documentation will be available at http://localhost:8080/swagger-ui/index.html

- Stop the application:
```bash
docker-compose down
```
