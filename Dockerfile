# Use the official OpenJDK 21 image as the base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged jar file from the target directory (after building the project)
COPY target/transaction_service-1.0-SNAPSHOT.jar transaction-service.jar

# Expose the port that the Spring Boot app will run on
EXPOSE 8080

# Define the entry point command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/transaction-service.jar"]