# Simple Banking Service Application

## Features

- Create and manage accounts
- Create and manage transactions

## Prerequisites

- JDK 11 or higher
- Maven 3.6+
- Docker

## Getting Started

### Database Setup with Docker Compose

1. Ensure Docker is installed and running on your local machine.
 
### Build and Run

1. Clone the repository.
2. Navigate to the project directory.
3. Start the application and PostgreSQL using Docker Compose:
    ```sh
    docker-compose up --build
    ```

## API Endpoints

### Account Controller

- **Create Account**
  - `POST /accounts`
  - Request Body: `AccountDTO`
  - Response: `201 Created` or `409 Conflict`

- **Get Account**
  - `GET /accounts/{id}`
  - Path Variable: `id` (Long)
  - Response: `200 OK` with `AccountDTO` or `404 Not Found`

### Transaction Controller

- **Create Transaction**
  - `POST /transactions`
  - Request Body: `TransactionDTO`
  - Response: `201 Created` or `400 Bad Request`


## Swagger
- http://localhost:8080/swagger-ui/index.html#/