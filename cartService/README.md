# Cart Service Application

## Description

Cart Service

### Implementation details

- runs on `localhost:8080` ...

## Installation

### Tooling

Tooling versions used in this example (others might work, but were not tested):

- Docker 19.03.2
- Docker Compose 1.24.1
- Keycloak 7.0.0

### Deployment

1. Clone the repo and change to the repo directory
2. mvn clean
3. optional: start docker desktop
4. start these services before: Frontend, krakend
5. Deploy docker containers with `docker-compose up --build`
6. Run tests with `mvn test`
7. Stop docker containers with `docker-compose down`


```
The service should be available at http://localhost:8080
```
