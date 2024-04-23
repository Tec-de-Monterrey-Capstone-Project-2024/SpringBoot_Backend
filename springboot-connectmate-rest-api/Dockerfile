# Base image with both Java and Maven installed.
FROM maven:3.8.4-openjdk-17 AS build
# Declares the working directory of the container.
WORKDIR /app
# Copies the pom.xml file to download dependencies first.
COPY pom.xml .
# Downloads the dependencies.
RUN mvn dependency:go-offline -B
# Copies the source code to the container.
COPY src ./src
# Builds the application (except for tests).
RUN mvn package -DskipTests

# Final Build Stage to reduce the image size.
FROM openjdk:17-alpine
# Copies the built JAR file from the previous stage.
COPY --from=build /app/target/springboot-connectmate-rest-api-0.0.1-SNAPSHOT.jar /app/springboot-connectmate-rest-api.jar
# Exposes the port where the application will be running.
EXPOSE 8080
# Command to run the application when the container starts.
CMD ["java", "-jar", "/app/springboot-connectmate-rest-api.jar"]

# To build the Docker image, run the following command:
# docker run -p 8080:8080 [user/image:version]
