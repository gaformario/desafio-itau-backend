
# Desafio Backend Itaú - API de Transações

Este projeto foi desenvolvido como parte de um desafio de programação backend promovido pelo Itaú Unibanco. A aplicação consiste em uma API REST, construída com Spring Boot, para o gerenciamento de transações financeiras e o cálculo de estatísticas em tempo real.

Repositório de origem do desafio: https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Maven
- Spring Web
- Spring Boot Actuator
- Lombok
- Swagger/OpenAPI 3
- Docker


## Configuração do Projeto

**1.** Clonar o projeto

```bash
git clone https://github.com/gaformario/desafio-itau-backend.git
```

**2.** Executar o projeto

```bash
mvn clean install
```

```bash
mvn spring-boot:run
```

**3.** Executar com Docker

 Criando a imagem
```bash
docker build -t desafio-itau-backend . 
```
Executando o container
```bash
docker run -p 8080:8080 desafio-itau-backend
```
**4.** Acesso à Aplicação
- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- Actuator: http://localhost:8080/actuator ou pelo próprio *Swagger UI*


## Documentação da API

#### `POST /transacao`
Registra uma nova transação.

**Request Body:**
```json
{
  "valor": 100.50,
  "dataHora": "2024-01-15T10:30:00Z"
}
```

**Exemplo de Uso**

```bash
curl -X 'POST' \
  'http://localhost:8080/transacao' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "valor": 100,
  "dataHora": "2025-06-06T21:33:24.197Z"
}'
```

#### `DELETE /transacao`
Deleta e limpa a lista de transações.

**Exemplo de Uso**

```bash
curl -X 'DELETE' \
  'http://localhost:8080/transacao' \
  -H 'accept: */*'
```

#### `GET /estatistica`
Obtém e retorna estatísticas das transações. Por padrão retorna sempre os últimos 60 segundos, porém é possível ajustar o parâmetro `intervalo` para 120, 240, etc.

**Query Parameters:**

* `intervalo` (opcional): Intervalo em segundos para buscar as transações. **defaultValue**: 60

**Response Body:**
```json
{
  "count": 10,
  "sum": 1000.50,
  "avg": 100.05,
  "min": 50.00,
  "max": 200.00
}
```

**Exemplos de Usos**

```bash
curl -X 'GET' \
  'http://localhost:8080/estatistica?intervalo=60' \
  -H 'accept: */*'
```

```bash
curl -X 'GET' \
  'http://localhost:8080/estatistica?intervalo=120' \
  -H 'accept: */*'
```
