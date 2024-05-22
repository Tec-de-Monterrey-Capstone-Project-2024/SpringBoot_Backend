#!/bin/sh

# Print environment variables for debugging
printenv

# Run the Java application with environment variables as JVM properties
exec java -jar /app/springboot-connectmate-rest-api.jar
