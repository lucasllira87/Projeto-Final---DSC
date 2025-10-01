# GreenTrack API - Full Project

Projeto Spring Boot completo para monitoramento de pegada de carbono.

## Variáveis de ambiente necessárias

- NEON_JDBC_URL (ex: jdbc:postgresql://<host>:5432/<db>?sslmode=require)
- NEON_DB_USER
- NEON_DB_PASSWORD
- JWT_SECRET

## Rodando localmente

```bash
export NEON_JDBC_URL="jdbc:postgresql://localhost:5432/greentrack"
export NEON_DB_USER="postgres"
export NEON_DB_PASSWORD="password"
export JWT_SECRET="change-me"

mvn clean package
java -jar target/greentrack-api-0.0.1-SNAPSHOT.jar
```

A documentação do Swagger estará disponível em `/swagger-ui.html`.
