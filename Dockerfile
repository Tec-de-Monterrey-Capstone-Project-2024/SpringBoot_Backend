# Base image with both Java and Maven installed.
FROM maven:3.8.4-openjdk-17 AS build
# Declares the arguments to be used in the build.
ARG PORT
ARG DB_HOST
ARG DB_NAME
ARG DB_USER
ARG DB_PASSWORD
ARG AWS_ACCESS_KEY
ARG AWS_SECRET_KEY
ARG AWS_REGION
# Declares the working directory of the container.
WORKDIR /app
# Copies the pom.xml file to download dependencies first.
COPY pom.xml .
# Downloads the dependencies.
RUN mvn dependency:go-offline -B
# Copies the source code to the container.
COPY src ./src
# Set environment variables from build-time arguments
ENV PORT=$PORT
ENV DB_HOST=$DB_HOST
ENV DB_NAME=$DB_NAME
ENV DB_USER=$DB_USER
ENV DB_PASSWORD=$DB_PASSWORD
ENV AWS_ACCESS_KEY=$AWS_ACCESS_KEY
ENV AWS_SECRET_KEY=$AWS_SECRET_KEY
ENV AWS_REGION=$AWS_REGION
# Builds the application (except for tests).
RUN mvn package -DskipTests

# Final Build Stage to reduce the image size.
FROM openjdk:17-alpine
# Copies the built JAR file from the previous stage.
COPY --from=build /app/target/springboot-connectmate-rest-api-0.0.1-SNAPSHOT.jar /app/springboot-connectmate-rest-api.jar
# Set environment variables from build-time arguments
ENV PORT=$PORT
ENV DB_HOST=$DB_HOST
ENV DB_NAME=$DB_NAME
ENV DB_USER=$DB_USER
ENV DB_PASSWORD=$DB_PASSWORD
ENV AWS_ACCESS_KEY=$AWS_ACCESS_KEY
ENV AWS_SECRET_KEY=$AWS_SECRET_KEY
ENV AWS_REGION=$AWS_REGION
# Exposes the port where the application will be running.
EXPOSE 8080
# Command to run the application when the container starts.
CMD ["sh", "-c", "echo 'PORT: ${8080}' && \
                    echo 'DB_HOST: ${DB_HOST}' && \
                    echo 'DB_NAME: ${DB_NAME}' && \
                    echo 'DB_USER: ${DB_USER}' && \
                    echo 'DB_PASSWORD: ${DB_PASSWORD}' && \
                    echo 'AWS_ACCESS_KEY: ${AWS_ACCESS_KEY}' && \
                    echo 'AWS_SECRET_KEY: ${AWS_SECRET_KEY}' && \
                    echo 'AWS_REGION: ${AWS_REGION}' && \
                    java  -DPORT=$PORT \
                    -DDB_HOST=${DB_HOST} \
                    -DDB_NAME=${DB_NAME} \
                    -DDB_USER=${DB_USER} \
                    -DDB_PASSWORD=${DB_PASSWORD} \
                    -DAWS_ACCESS_KEY=${AWS_ACCESS_KEY} \
                    -DAWS_SECRET_KEY=${AWS_SECRET_KEY} \
                    -DAWS_REGION=${AWS_REGION} \
                    -jar /app/springboot-connectmate-rest-api.jar"]