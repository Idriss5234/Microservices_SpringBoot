FROM maven:3.6.3-jdk-11 AS build

WORKDIR /app

COPY pom.xml .
COPY src src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

COPY --from=build /app/target/micro-service-panier-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8091

CMD [ "java","-jar","/app.jar" ]