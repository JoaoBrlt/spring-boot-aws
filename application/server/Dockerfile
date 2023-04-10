FROM eclipse-temurin:17.0.6_10-jre-alpine
WORKDIR /app
COPY target/*.jar /app/server.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "server.jar"]
