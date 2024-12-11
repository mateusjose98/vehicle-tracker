# para buildar com logs: docker build --no-cache --progress=plain -t api:1.0 .
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app

COPY . .
RUN mvn clean package -f pom.xml

# Etapa 2: Runtime
FROM openjdk:17-jdk-slim
WORKDIR /demo
COPY --from=build /app/app-client-lib/target/*.jar demo.jar

ENTRYPOINT ["java", "-jar", "demo.jar"]
