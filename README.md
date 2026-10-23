# Logistica Cadeia de Suprimentos 

> Projeto Final da disciplina de Backend (Engenharia de Software, SATC). Etapa 1: arquitetura do backend.

Sistema de controle de estoque para logística e cadeia de suprimentos. Permite cadastrar produtos com quantidade atual e estoque mínimo, listar todos os itens cadastrados e buscar um item pelo código. A API garante que o estoque atual nunca seja menor que o estoque mínimo.

## Índice

- [Integrantes](#integrantes)
- [O problema](#o-problema)
- [Tecnologias](#tecnologias)
- [Arquitetura](#arquitetura)
- [Entidades](#entidades)
- [Rotas](#rotas)
- [Como executar](#como-executar)
- [Situação do projeto](#situação-do-projeto)
- [Documentação](#documentação)

## Integrantes

| Nome |
|---|
| Andrey Henrique João |
| Cauã Savi Da Silva Rosa |
| Davi Steiner Freitas |

## O problema

**Tema sorteado:** Logística e Cadeia de Suprimentos

O controle manual de estoque dificulta saber quando um produto está abaixo do nível mínimo necessário para continuar operando. O sistema cadastra produtos com estoque atual e estoque mínimo, impedindo que um item seja cadastrado com estoque atual menor que o mínimo, o que ajuda a evitar rupturas de estoque na cadeia de suprimentos.

**Quem usa o sistema:**

| Quem | O que faz no sistema |
|---|---|
| Gestor de estoque | Cadastra produtos, consulta a lista de itens e busca um produto pelo código |

**Regras de negócio.** O grupo criou estas três regras:

| # | Regra | Onde está no código | Rota que usa |
|---|---|---|---|
| R1 | O estoque atual não pode ser menor que o estoque mínimo | `ControleEstoqueService` | `POST /controle-estoque` |

## Tecnologias

| O quê | Qual |
|---|---|
| Linguagem | Java 25 |
| Framework | Spring Boot 3.5 (Web, Validation, Data JPA) |
| Banco de dados | PostgreSQL 16, em Docker |
| Migrations | Flyway |
| Documentação da API | springdoc-openapi (Swagger UI) |

## Arquitetura

A API é organizada em camadas. Uma requisição entra pelo Controller, passa pelo Service, que aplica as regras de negócio, e chega ao banco pelo Repository; a resposta faz o caminho de volta. O Mapper converte DTO em entidade e entidade em DTO.

**Mapa da estrutura.** Uma linha por entidade, todas as do modelo. Em cada célula, a classe e a situação: **pronta**, **implementada** (já tem lógica), **em parte** (só uma parte das rotas) ou **montada** (criada, ligada às outras, ainda sem lógica).

| Entidade | Migration | Model | Repository | DTOs | Mapper | Service | Controller |
|---|---|---|---|---|---|---|---|
| Controle de estoque | `V1__criar_controle_estoque.sql`, pronta | `ControleEstoque`, implementada | `ControleEstoqueRepository`, pronto | `ControleEstoqueRequestDTO`, `ControleEstoqueResponseDTO`, prontos | `ControleEstoqueMapper`, implementado | `ControleEstoqueService`, implementado | `ControleEstoqueController`, implementado |

## Entidades

| Entidade | O que representa | Como se relaciona |
|---|---|---|
| Controle de estoque | Produto com quantidade atual e estoque mínimo | Nenhuma relação nesta etapa (apenas esta entidade) |

Atributos, tipos e o diagrama das tabelas estão em [docs/modelo-de-dominio.md](docs/modelo-de-dominio.md).

## Rotas

### As que já funcionam

| Verbo | Caminho | O que faz | Regra | Sucesso | Erros |
|---|---|---|---|---|---|
| `POST` | `/controle-estoque` | Cadastra um produto no estoque | R1 | `201` | `400` |
| `GET` | `/controle-estoque` | Lista todos os produtos do estoque | Nenhuma | `200` | Nenhum |
| `GET` | `/controle-estoque/{codigo}` | Busca um produto pelo código | Nenhuma | `200` | `404` |

Exemplo de requisição:

```http
POST /controle-estoque
Content-Type: application/json

{
  "produto": "Caneta esferografica",
  "estoqueAtual": 120,
  "estoqueMinimo": 20
}
```

Resposta, `201 Created`:

```json
{
  "codigo": 1,
  "produto": "Caneta esferografica",
  "estoqueAtual": 120,
  "estoqueMinimo": 20
}
```

### Erros

Todo erro sai no mesmo formato. Este é o `400` de uma regra de negócio violada:

```json
{
  "status": 400,
  "erro": "Bad Request",
  "mensagem": "R1: o estoque atual nao pode ser menor que o estoque minimo",
  "caminho": "/controle-estoque",
  "timestamp": "2026-10-22T16:37:40",
  "campos": []
}
```

Um campo inválido também devolve `400`, com a lista `campos` dizendo qual campo corrigir. Um id que não existe devolve `404`.

### As planejadas

Estão no contrato, mas ainda não respondem.

| Recurso | O que vai fazer |
|---|---|
| Controle de frota | Cadastra veículos, motoristas, rotas e status da frota |
| Rastreamento de entregas | Acompanha entregas, clientes, destinos e previsão |

O contrato completo, com todas as rotas e os DTOs, está em [docs/contrato-da-api.md](docs/contrato-da-api.md).

## Como executar

Pré-requisitos: JDK 25, Docker Desktop aberto e Git.

```bash

docker compose up -d          # sobe o PostgreSQL na porta 5437
./mvnw spring-boot:run        # Windows: .\mvnw.cmd spring-boot:run
```

Com a API no ar:

| Endereço | O que é |
|---|---|
| <http://localhost:8080/swagger-ui.html> | Swagger UI: as rotas, com **Try it out** para enviar requisições |
| <http://localhost:8080/v3/api-docs> | O contrato OpenAPI em JSON |

Para testar as rotas, use o Swagger UI ou o **Postman**. No Postman, importe todas as rotas de uma vez: **Import** → **Link** → `http://localhost:8080/v3/api-docs`.

Para parar: `Ctrl+C` na API e `docker compose stop` no banco. Para apagar o banco e rodar as migrations do zero: `docker compose down -v` e suba de novo.

## Situação do projeto

| Parte | Situação |
|---|---|
| Desenho | Completo: problema e regras neste README, modelo e contrato emdocs/ |
| Estrutura | a entidade controle de estoque tem migration, entidade, Repository, DTOs, Mapper, Service e Controller |
| API e regras | as rotas de controle de estoque funcionam;|

## Documentação

| Documento | O que tem |
|---|---|
| [Modelo de domínio](docs/modelo-de-dominio.md) | Entidades, relacionamentos, diagrama das tabelas, migrations |
| [Contrato da API](docs/contrato-da-api.md) | Todas as rotas, os DTOs, exemplos de JSON e erros |
| [Autoavaliação](docs/autoavaliacao.md) | Os critérios da revisão do código, marcados pelo grupo |
