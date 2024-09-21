# transaction_service
Transaction Service

This project is a Spring Boot application that provides a simple service to manage accounts and transactions. It includes:

	•	Account creation and retrieval.
	•	Transaction creation for operations like purchases, withdrawals, and credit vouchers.
	•	Swagger UI for API documentation.
	•	Docker support for easy containerization.
	•	JUnit 5 and Mockito tests for both controllers and services.

Features

	•	Create and retrieve accounts.
	•	Create transactions.
	•	Swagger for API documentation.
	•	Docker for containerization.
	•	SQL Database integration (H2 or any RDBMS).
	•	JUnit 5 for unit testing.

Prerequisites

Before running the project, ensure you have the following installed:

	•	Java 21
	•	Maven
	•	Docker (for containerization)
	•	MySQL (for external database usage)

1. Running the Application

Step 1: Clone the repository

git clone https://github.com/Gauravm017/transaction_service.git
cd transaction-service

Step 2: Configure the Database

Modify src/main/resources/application.properties to set up your database connection
spring.datasource.url=jdbc:mysql://localhost:3306/transaction_service
spring.datasource.username=root
spring.datasource.password=gaur1010
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

2. Running the Application with Docker 
    
Step 1: Build Docker Image

    docker build -t transaction-service .

Step 2: Run the run Script

    ./run.sh

3. API Documentation with Swagger

After running the application, you can access the Swagger UI to explore and test the API.
    
    •   Swagger UI: http://localhost:8080/swagger-ui.html
	•   OpenAPI JSON: http://localhost:8080/v3/api-docs

Available Endpoints:

	•	POST /accounts – Create a new account.
	•	GET /accounts/{accountId} – Retrieve account by ID.
	•	POST /transactions – Create a new transaction.


Example cURL Commands:

	•	Create an Account:

       curl -X POST http://localhost:8080/accounts \
     -H "Content-Type: application/json" \
     -d '{
           "document_number": "12345678900"
         }'
=======================

    •	Get Account by ID:

    curl -X GET http://localhost:8080/accounts/1 \
     -H "Content-Type: application/json"


========================

    •	Create a Transaction:

    curl -X POST http://localhost:8080/transactions \
     -H "Content-Type: application/json" \
     -d '{
           "account_id": 1,
           "operation_type_id": 4,
           "amount": 123.45
         }'





