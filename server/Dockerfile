FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml /app
COPY src /app/src
RUN mvn package -DskipTests

FROM eclipse-temurin:17.0.6_10-jre-alpine AS run
WORKDIR /app
COPY --from=build /app/target/*.jar /app/server.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "server.jar"]
