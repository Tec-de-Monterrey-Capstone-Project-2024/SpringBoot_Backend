FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/springboot-connectmate-rest-api-0.0.1-SNAPSHOT.jar /app/springboot-connectmate-rest-api.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/springboot-connectmate-rest-api.jar"]