# Usa una imagen
FROM maven:3.8.4-openjdk-17 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo pom.xml para descargar las dependencias primero
COPY pom.xml .

# Descarga las dependencias
RUN mvn dependency:go-offline -B

# Copia todos los archivos fuente al contenedor
COPY src ./src

# Compila la aplicación omitiendo los tests
RUN mvn package -DskipTests

# Etapa de construcción final para reducir el tamaño de la imagen
FROM openjdk:18-alpine

# Copia el artefacto construido desde la etapa anterior
COPY --from=build /app/target/springboot-connectmate-rest-api-0.0.1-SNAPSHOT.jar /app/app.jar

# Expone el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicia el contenedor
CMD ["java", "-jar", "/app/app.jar"]
