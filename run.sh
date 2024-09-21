#!/bin/bash

# Step 1: Clean and build the Maven project
echo "Building the project with Maven..."
mvn clean package -DskipTests

# Step 2: Build the Docker image
echo "Building the Docker image..."
docker build -t transaction-service .

# Step 3: Run the Docker container
echo "Running the Docker container..."
docker run -p 8080:8080 -e MYSQL_HOST=localhost --name transaction-service-container -d transaction-service

echo "The application is running on http://localhost:8080"