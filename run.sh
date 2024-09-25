#!/bin/bash

# Step 1: Clean and build the Maven project
echo "Building the project with Maven..."
mvn clean package

# Step 2: Build the Docker image
echo "Building the Docker image..."
docker build -t transaction-service .

# Step 3: Run the Docker container with a specific Spring profile
# Replace 'dev' with the desired profile name (e.g., dev, prod, test)
SPRING_PROFILE=docker
MYSQL_URL=localhost
echo "Running the Docker container with Spring profile: $SPRING_PROFILE..."
docker run -p 8080:8080 \
    -e MYSQL_HOST=$MYSQL_URL \
    -e SPRING_PROFILES_ACTIVE=$SPRING_PROFILE \
    --name transaction-service-container \
    -d transaction-service

echo "The application is running on http://localhost:8080 with profile: $SPRING_PROFILE and MYSQL Host: $MYSQL_URL"