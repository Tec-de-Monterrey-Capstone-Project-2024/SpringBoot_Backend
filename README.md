# SpringBoot Connectmate Rest API

## Overview

The SpringBoot Connectmate Rest API is a backend application designed to facilitate connections and interactions to the Connectmate App. Leveraging the power of Spring Boot, this application provides a robust and scalable platform for developing RESTful APIs.

## Setup Guide

This guide is designed to help you quickly set up and run the SpringBoot Connectmate Rest API on your local environment for development and testing purposes.

### Prerequisites

Ensure you have the following installed on your system before proceeding:

- **Java JDK 17**: Required for running Java applications.
- **Maven**: Necessary for dependency management and project building.
- **MySQL**: The database used for storing application data.

### Getting Started

Follow these steps to get your development environment running:

1. **Clone the Project**

   Use Git to clone the project repository to your local machine:

   ```sh
   git clone https://github.com/Tec-de-Monterrey-Capstone-Project-2024/SpringBoot_Backend.git
   ```

2. **Project Setup**

   Navigate to the project directory:

   ```sh
   cd springboot-connectmate-rest-api
   ```

   Build the project using Maven:

   ```sh
   mvn clean install
   ```

3. **Running the Application**

   Start the application with Maven:

   ```sh
   mvn spring-boot:run
   ```

   The application will be available at `http://localhost:8080`.

## Key Technologies & Dependencies

- **Spring Boot (3.2.4)**: Simplifies the bootstrapping and development of new Spring applications.
- **Java 17**: The core programming language used.
- **Maven**: Manages dependencies and builds the project.
- **Spring Data JPA**: Facilitates database operations using JPA.
- **Spring Security**: Provides authentication and authorization capabilities.
- **Spring Web**: Supports the creation of web applications, including RESTful services.
- **Amazon Bedrock**: Integrates artificial intelligence functionalities into Spring applications.
- **MySQL Driver**: Connects your application to MySQL databases.
- **Lombok**: Simplifies data object creation by reducing boilerplate code.
- **Spring Boot DevTools**: Enhances development with features like automatic restart.
- **Spring Test & Security Test**: Supports testing of Spring components.
- **SpringDoc OpenAPI Starter WebMVC UI (2.5.0)**: Generates API documentation based on the OpenAPI 3 specification.

## Documentation

Explore the API documentation through these interfaces after launching the application:

- **Swagger UI**: Interactive documentation at `http://localhost:8080/swagger-ui/index.html`. Explore API endpoints, execute calls, and review responses directly in your browser.
  
- **OpenAPI Specs**: Access the complete OpenAPI 3 specification in JSON format at `http://localhost:8080/v3/api-docs`. Useful for generating client libraries or integrating with other OpenAPI-compatible tools.


## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.
