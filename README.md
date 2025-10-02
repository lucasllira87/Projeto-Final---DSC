# GreenTrack API

GreenTrack é uma API REST construída com **Spring Boot**, **Spring Data JPA** e **Spring Security**.  
Ela permite gerenciar usuários, atividades e registros de carbono.

---

## 🔹 Tecnologias

- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Security
- PostgreSQL
- H2 (opcional, para testes)
- Maven

---

## 🔹 Requisitos

- Java 21 ou superior
- Maven
- PostgreSQL (ou NeonDB para produção)
- IDE (IntelliJ, VSCode, Eclipse)

---

## 🔹 Configuração do Banco de Dados

No arquivo `application.properties` ou `application.yml`, configure sua conexão:

```properties
# Local
spring.datasource.url=jdbc:postgresql://localhost:5432/greentrackdb
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update

# Produção (exemplo NeonDB)
spring.datasource.url=jdbc:postgresql://ep-lucky-unit-a8fv7hxa-pooler.eastus2.azure.neon.tech/neondb?sslmode=require
spring.datasource.username=neondb_owner
spring.datasource.password=npg_XYbpd3qZ0ons
Certifique-se que o PostgreSQL está rodando e que você tem permissão para acessar o banco.

🔹 Rodando a API
Clone o repositório:

bash
Copiar código
git clone https://github.com/usuario/greentrack-api.git
cd greentrack-api
Compile e rode a aplicação com Maven:

bash
Copiar código
mvn spring-boot:run
A API será iniciada em: http://localhost:8080/

🔹 Endpoints
Autenticação
Método	Endpoint	Descrição
POST	/auth/login	Login e recebimento do token JWT

Usuários
Método	Endpoint	Descrição
GET	/users	Lista todos os usuários
POST	/users	Cria um novo usuário
GET	/users/{id}	Busca usuário por ID
PUT	/users/{id}	Atualiza usuário
DELETE	/users/{id}	Remove usuário

Atividades
Método	Endpoint	Descrição
GET	/activities	Lista todas as atividades
POST	/activities	Cria uma nova atividade

Logs de Usuário
Método	Endpoint	Descrição
GET	/logs	Lista todos os logs
POST	/logs	Cria um novo log

⚠️ Todos os endpoints, exceto /auth/** e Swagger (/swagger-ui/**), exigem autenticação.

🔹 Testando com curl
Login:

bash
Copiar código
curl -X POST http://localhost:8080/auth/login \
-H "Content-Type: application/json" \
-d '{"username":"usuario","password":"senha"}'
Acessando rota protegida com token JWT:

bash
Copiar código
curl -X GET http://localhost:8080/users \
-H "Authorization: Bearer SEU_TOKEN_AQUI"
🔹 Swagger UI
Se você adicionou Swagger:

bash
Copiar código
http://localhost:8080/swagger-ui/index.html
🔹 Observações
Use o Postman ou Insomnia para testar endpoints facilmente.

Configure corretamente o banco antes de rodar a API.

Para produção, utilize NeonDB ou outro banco PostgreSQL remoto.


