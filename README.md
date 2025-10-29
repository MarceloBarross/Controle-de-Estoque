# API Controle de Estoque 

_API RESTful desenvolvida com Spring Boot para gerenciamento de estoque, incluindo controle de itens, funcionários, autenticação/autorização e auditoria detalhada de ações._



## Funcionalidades Principais

**Gerenciamento de Itens:**
    * CRUD completo (Criar, Ler, Atualizar, Deletar) para itens do estoque.
    * Operações de Entrada e Saída de quantidade no estoque.
**Gerenciamento de Funcionários:**
    * CRUD completo para funcionários.
    * Distinção de Cargos (ex: ADMIN, COLABORADOR).
**Autenticação & Autorização:**
    * Endpoint de login para autenticação via Email/Senha.
    * Uso de Tokens (presumivelmente JWT) para autorização de acesso aos endpoints.
    * Controle de acesso baseado em Roles (ex: ADMIN tem acesso total, outros usuários têm acesso limitado).
**Auditoria:**
    * Registro detalhado de todas as ações de escrita (Criação, Alteração, Exclusão, Entrada, Saída) no sistema.
    * Log inclui: tabela afetada, tipo de ação, ID do registro afetado, estado ANTES e DEPOIS da alteração (em JSON), ID e nome do usuário responsável, e timestamp.

---

## Tecnologias Utilizadas

**Linguagem:** Java 21
**Framework Principal:** Spring Boot 3.5.4
**Persistência:**
    * Spring Data JPA
    * Hibernate 6.6
    * PostgreSQL 17.5
**Migrações de Banco:** Flyway
**Segurança:** Spring Security 6.5
**Utilitários:**
    * Lombok (redução de boilerplate)
    * MapStruct (mapeamento DTO/Model - presumido)
    * Jackson (manipulação JSON)
**Build Tool:** Maven (presumido)

---

## Pré-requisitos

* JDK 21 ou superior instalado.
* Maven 3.x instalado.
* Instância do PostgreSQL rodando (localmente ou em container).

---

## Configuração e Instalação

1.  **Clone o Repositório:**
    ```bash
    git clone <url-do-seu-repositorio>
    cd ControleDeEstoque
    ```

2.  **Configure o Banco de Dados PostgreSQL:**
    * Crie um banco de dados chamado `controledeestoque`.
    * Crie um usuário (ex: `usuario`) com uma senha (ex: `senha`).
    * Conceda as permissões necessárias para este usuário no banco `controledeestoque`.

3.  **Configure a Aplicação (`application.properties`):**
    Verifique/crie o arquivo `src/main/resources/application.properties` e certifique-se que as configurações do banco de dados estão corretas:
    ```properties
    # Configuração do Banco de Dados PostgreSQL
    spring.datasource.url=jdbc:postgresql://localhost:5432/controledeestoque
    spring.datasource.username=usuario
    spring.datasource.password=senha

    # Configuração do Driver (geralmente não necessário com Spring Boot 3)
    # spring.datasource.driver-class-name=org.postgresql.Driver

    # Configuração do JPA/Hibernate
    spring.jpa.hibernate.ddl-auto=validate # Hibernate apenas valida o schema criado pelo Flyway
    spring.jpa.show-sql=true # Mostra os SQLs gerados (útil para debug)
    spring.jpa.properties.hibernate.format_sql=true # Formata os SQLs no log

    # Configuração do Flyway (ativado por padrão)
    spring.flyway.enabled=true

    # (Opcional) Configuração do JWT Secret (se estiver usando)
    # api.security.token.secret=SEU_SEGREDO_SUPER_SECRETO_AQUI
    ```

4.  **Crie o Usuário de Teste Inicial (Opcional, mas útil):**
    Insira um usuário padrão (como o usado temporariamente nos controllers):
    ```sql
    INSERT INTO funcionarios (id, nome, cargo, email, senha, ativo)
    VALUES ('a05a8d55-08c5-4642-b026-e63327e7bb9f', 'Usuário Teste API', 'ADMIN', 'testeapi@sistema.com', '$2a$10$....', true) -- Use um hash bcrypt válido!
    ON CONFLICT (id) DO NOTHING;
    -- (Ajuste o hash da senha ou insira manualmente no banco)
    ```

5.  **Compile o Projeto:**

---

## Executando a Aplicação

Importe o projeto como um projeto Maven e execute a classe principal `ControleDeEstoqueApplication.java`.

A API estará disponível em `http://localhost:8080`.

---

## Autenticação

1.  **Obtenha um Token:** Envie uma requisição `POST` para `/funcionarios/login` com o seguinte corpo JSON:
    ```json
    {
      "email": "email_do_usuario@email.com",
      "senha": "senha_do_usuario"
    }
    ```
2.  **Resposta:** A API retornará um token (presumivelmente JWT) no corpo da resposta:
    ```json
    {
      "token": "seu_token_jwt_aqui"
    }
    ```
3.  **Use o Token:** Para acessar endpoints protegidos, inclua o token no cabeçalho `Authorization` de cada requisição:
    ```
    Authorization: Bearer seu_token_jwt_aqui
    ```

---

## Endpoints da API

_(**Nota:** Os paths abaixo seguem as convenções REST recomendadas. Ajuste se seus paths forem diferentes.)_

### Funcionários (`/funcionarios`)

* **`/funcionarios/login`**
    * **Descrição:** Autentica um funcionário.
    * **Autenticação:** Pública (`permitAll`).
    * **Request Body:** `AuthenticationDTO` (`{ "email": "...", "senha": "..." }`)
    * **Response:** `200 OK` com `LoginResponseDTO` (`{ "token": "..." }`) ou `401 Unauthorized`.
* **`/funcionarios/POST`**
    * **Descrição:** Cria um novo funcionário.
    * **Autenticação:** Requer Role `ADMIN`.
    * **Request Body:** `FuncionariosDTO` (sem ID, com senha em texto plano).
    * **Response:** `201 Created` com o `FuncionariosDTO` criado (sem senha).
* **`/funcionarios/GET`**
    * **Descrição:** Lista todos os funcionários (ativos).
    * **Autenticação:** Requer usuário autenticado.
    * **Response:** `200 OK` com `List<FuncionariosDTO>`.
* **`/funcionarios/GET/{id}`**
    * **Descrição:** Busca um funcionário pelo ID.
    * **Autenticação:** Requer usuário autenticado.
    * **Response:** `200 OK` com `FuncionariosDTO` ou `404 Not Found`.
* **`/funcionarios/PUT/{id}`**
    * **Descrição:** Atualiza os dados de um funcionário (requer envio do DTO completo ou lógica PATCH).
    * **Autenticação:** Requer Role `ADMIN`.
    * **Request Body:** `FuncionariosDTO` com os dados a serem atualizados.
    * **Response:** `200 OK` com o `FuncionariosDTO` atualizado ou `404 Not Found`.
* **`/funcionarios/DELETE/{id}`**
    * **Descrição:** Deleta um funcionario do BD.
    * **Autenticação:** Requer Role `ADMIN`.
    * **Response:** `204 No Content` ou `404 Not Found`.

### Itens (`/itens`)

* **`/itens/POST`**
    * **Descrição:** Cria um novo item no estoque.
    * **Autenticação:** Requer usuário autenticado.
    * **Request Body:** `ItensDTO` (sem ID).
    * **Response:** `201 Created` com o `ItensDTO` criado.
* **`/itens/GET`**
    * **Descrição:** Lista todos os itens.
    * **Autenticação:** Requer usuário autenticado.
    * **Response:** `200 OK` com `List<ItensDTO>`.
* **`/itens/GET/{id}`**
    * **Descrição:** Busca um item pelo ID.
    * **Autenticação:** Requer usuário autenticado.
    * **Response:** `200 OK` com `ItensDTO` ou `404 Not Found`.
* **`/itens/PUT/{id}`**
    * **Descrição:** Atualiza os dados de um item (Nome, Descrição, Quantidade - lógica PATCH implementada).
    * **Autenticação:** Requer usuário autenticado.
    * **Request Body:** `ItensDTO` (apenas com os campos a serem atualizados).
    * **Response:** `200 OK` com o `ItensDTO` atualizado ou `404 Not Found` / `400 Bad Request`.
* **`/itens/DELETE/{id}`**
    * **Descrição:** Deleta um item do estoque.
    * **Autenticação:** Requer usuário autenticado (ou talvez `ADMIN`?).
    * **Response:** `204 No Content` ou `404 Not Found`.
* **`/itens//PUT/entrada/{id}`**
    * **Descrição:** Registra a entrada de uma quantidade no estoque do item.
    * **Autenticação:** Requer usuário autenticado.
    * **Request Body:** `QtdDTO` (`{ "quantidade": valor_inteiro_positivo }`). _(Você precisa criar este DTO)_.
    * **Response:** `200 OK` com o `ItensDTO` atualizado ou `404 Not Found` / `400 Bad Request`.
* **`/itens//PUT/saida/{id}`**
    * **Descrição:** Registra a saída de uma quantidade no estoque do item.
    * **Autenticação:** Requer usuário autenticado.
    * **Request Body:** `QtdDTO` (`{ "quantidade": valor_inteiro_positivo }`). _(Você precisa criar este DTO)_.
    * **Response:** `200 OK` com o `ItensDTO` atualizado ou `404 Not Found` / `400 Bad Request` (estoque insuficiente).

---

## Auditoria

Todas as operações que modificam o estado dos `itens` ou `funcionarios` geram um registro na tabela `registros`. Esta tabela armazena:
* Qual tabela foi afetada (`tabela_afetada`).
* Qual ação foi realizada (`tipo`: CRIACAO, ALTERACAO, EXCLUSAO, ENTRADA, SAIDA).
* Qual registro específico foi afetado (`id_registro_afetado`).
* O estado do registro **antes** da alteração (`valor_anterior` em formato JSON).
* O estado do registro **depois** da alteração (`valor_novo` em formato JSON).
* Quem realizou a ação (`id_usuario`, `usuario`).
* Quando a ação ocorreu (`data_registro`).
