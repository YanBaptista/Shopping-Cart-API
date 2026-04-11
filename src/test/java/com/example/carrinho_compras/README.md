# 🛒 Shopping Cart API

> Uma API RESTful desenvolvida em Java com Spring Boot para gerenciamento de um carrinho de compras. O sistema permite adicionar produtos, calcular totais dinâmicos (considerando quantidades e preços fracionados) e aplicar cupons de desconto, tudo com validações rigorosas de regras de negócio.
>
> 🎓 **Contexto Acadêmico:** Este projeto é um requisito prático para a disciplina de **Engenharia de Software (C14)** do **Instituto Nacional de Telecomunicações (INATEL)**, ministrada pelo professor **Christopher Lima**.

---

## 🚀 Tecnologias Utilizadas

| Categoria | Tecnologia |
|---|---|
| Linguagem | Java 21 |
| Framework | Spring Boot 4.0.5 (Spring Web) |
| Gerenciador de Dependências | Maven |
| Testes | JUnit 5 |
| CI/CD | GitHub Actions |
| Deploy / Hospedagem | Render |

---

## ⚙️ Funcionalidades e Regras de Negócio

- ✅ Adição de itens ao carrinho de compras.
- ✅ Cálculo do valor total considerando múltiplas unidades do mesmo produto.
- ✅ Sistema de desconto com cupons (ex: `BEMVINDO10` para 10% de desconto).
- 🚫 Bloqueio de produtos com **preço negativo**.
- 🚫 Bloqueio de produtos com **quantidade zero ou negativa**.
- 🚫 Bloqueio de **produtos nulos** ou com dados inválidos.
- 🚫 Rejeição de **cupons inválidos** ou com letras minúsculas.

---

## 🌐 Endpoints da API

| Método | Rota | Descrição |
|--------|------|-----------|
| `GET` | `/carrinho/itens` | Retorna a lista em JSON de todos os produtos no carrinho. |
| `GET` | `/carrinho/total` | Retorna o valor numérico total da compra. |
| `POST` | `/carrinho/adicionar` | Recebe um JSON no Body (`id`, `nome`, `preco`, `quantidade`) para adicionar ao carrinho. |
| `POST` | `/carrinho/cupom?codigo=NOME_DO_CUPOM` | Aplica um cupom de desconto sobre o total. |

### Exemplo de Body para `POST /carrinho/adicionar`

```json
{
  "id": 1,
  "nome": "Notebook",
  "preco": 3500.00,
  "quantidade": 2
}
```

---

## 🧪 Cobertura de Testes

O projeto conta com uma suíte robusta de testes automatizados dividida em duas camadas:

- **Testes Unitários (`Service`):** Cobrem a camada de lógica de negócio com cenários de fluxo normal (caminho feliz), casos de borda e disparo de `Exceptions` para entradas de dados inválidas. Foram implementados **10 cenários de fluxo normal** e **10 cenários de fluxo de extensão (casos inoportunos)**, totalizando 20 testes.

---

## 🤖 Pipeline CI/CD

A Integração e Entrega Contínua foi implementada com **GitHub Actions** (`.github/workflows/`).

### Estrutura do Pipeline

O workflow possui **3 jobs distintos**, com execução em paralelo onde aplicável:

```text
┌─────────────────────────────────────────────┐
│                  on: push / PR              │
└──────────┬──────────────────────┬────────────┘
           │                      │
    ┌──────▼──────┐        ┌──────▼──────┐
    │   🧪 test   │        │  📦 build   │  ← rodam em paralelo
    └──────┬──────┘        └──────┬──────┘
           └──────────┬───────────┘
                ┌─────▼─────┐
                │ 🚀 deploy │  ← só executa após sucesso dos anteriores
                └─────┬─────┘
                      │
               ┌──────▼──────┐
               │ 📧 notify   │
               └─────────────┘
```

### Destaques do Pipeline

- **✔️ Feedback claro:** O pipeline reporta explicitamente sucesso ou falha dos testes e do build antes de prosseguir.
- **📦 Artifacts:** O pacote compilado (`.jar`) e o relatório de execução dos testes são armazenados automaticamente como **Artifacts** na aba Actions do GitHub.
- **🔒 Sem hardcode:** O endereço de e-mail de notificação é configurado como **Secret/Variable** no GitHub Actions — nunca fixado diretamente no código.
- **📥 Dependências automáticas:** Todas as dependências são instaladas via Maven a partir do `pom.xml` dentro do próprio workflow.
- **🛡️ Deploy seguro:** O job de deploy só é executado após o sucesso completo dos testes e do build, garantindo que apenas versões válidas sejam publicadas.

---

## 🌍 Deploy

A API está hospedada e rodando na nuvem através do **Render**.

🔗 **Acesse a API em produção:**
> [`https://shopping-cart-api-seb6.onrender.com/carrinho/itens`](https://shopping-cart-api-seb6.onrender.com/carrinho/itens)

---

## 📂 Estrutura do Projeto

```text
carrinho-compras/
├── .github/
│   └── workflows/
│       └── ci-cd.yml                          # Pipeline do GitHub Actions
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com.example.carrinho_compras/
│   │           ├── controller/
│   │           │   └── CarrinhoController.java
│   │           ├── model/
│   │           │   └── Produto.java
│   │           ├── repository/
│   │           └── service/
│   │               └── (CarrinhoService)
│   │           └── CarrinhoComprasApplication.java
│   └── resources/
├── test/
│   └── java/
│       └── com.example.carrinho_compras/
│           └── CarrinhoComprasApplicationTests.java
├── .gitattributes
├── .gitignore
├── Dockerfile
├── mvnw / mvnw.cmd
├── pom.xml                                    # Gerenciamento de dependências (Maven)
└── README.md
```

---

## 🤖 Uso de Inteligência Artificial no Projeto

Nesta seção, declaramos como a IA (**Gemini**) foi integrada ao ciclo de desenvolvimento prático para elevar a qualidade do software e garantir a robustez das regras de negócio e da documentação.

### 📝 Prompts Utilizados e Resultados Obtidos

- **Prompt 1 (Ideia do projeto):** *"Chat, preciso fazer esse trabalho, mas antes preciso de ideias de projeto. Quero algo simples para poder focar em testes e CI/CD. Primeiro, quero apenas algumas ideias. Vai ser em Java." ( mandei o aquivo do trabalho)*
  - **Resultado:** A IA exibiu opções de projeto para análise e escolhas, dentre eles estavam as ideias de uma calculadora e um carrinho de compras com Spring Boot. O carrinho foi a ideia escolhida.

- **Prompt 2 (Engenharia de Testes):** *"Ele pediu para fazermos 10 testes. 5 inoportunos e 5 normais. Lembra desses testes inoportunos e normais e o que significam?"*
  - **Resultado:** A IA clarificou os conceitos de *Caminho Feliz (Happy Path)* e *Casos Extremos (Edge Cases)*, estruturando o código para atingir a meta de testes estabelecida pela disciplina.

- **Prompt 3 (Test-Driven Development e Controle de Versão):** *"Deu falha em 2 testes. Olhe no print. Antes de continuarmos, eu esqueci de criar uma nova branch, me ajuda com isso? Outra coisa, me instrua passo a passo na missão que você pediu para fazer os testes rodarem tranquilos..."*
  - **Resultado:** A IA instruiu a criação segura de uma nova branch (`test/novos-cenarios-wagner`) via interface do IntelliJ e guiou a implementação de defesas (TDD) no `CarrinhoService.java` para barrar a entrada de produtos nulos ou sem nome, resolvendo as falhas apontadas pelo JUnit.

- **Prompt 4 (Documentação):** *"Temos o esboço do Read Me. Podemos completar com o que já temos"*
  - **Resultado:** Auxílio na formatação e unificação da documentação final, agregando as informações do pipeline CI/CD estruturado pelo grupo com os requisitos de Contexto Acadêmico e Declaração de IA.

- **Prompt 5 (Testes Unitários - Caminho Feliz):** *"vamos começar a fazer os teste de fluxo de sucesso primeiro, vamos fazer 5"*
  - **Resultado:** A IA auxiliou na ideação e criação dos primeiros 5 testes unitários focados no "caminho feliz" (Happy Path), garantindo que a adição de produtos, cálculo de valores e verificação de tamanhos de lista funcionassem perfeitamente sob condições ideais.

- **Prompt 6 (Refatoração e Regras de Negócio):** *"quero mudar o ultimo teste, quero testa se o nome for minusculo dar falso, pois deixei o cupom só valido com letra maiuculas"*
  - **Resultado:** A IA orientou a implementação de uma validação rigorosa (IllegalArgumentException) no código e forneceu o teste de unidade correspondente (assertThrows) para rejeitar cupons digitados em minúsculo, fortalecendo a segurança da aplicação.

- **Prompt 7 (Planejamento de Arquitetura):** *"Depois que criamos nossos teste, qual é o proximo passo ? quando vamos criar nosso ci/cd ?"*
  - **Resultado:** A IA mapeou o roteiro do desenvolvimento, explicando a ordem cronológica correta (arquitetura em camadas): estruturar primeiro os Controllers (rotas Web) e só depois partir para a automação de infraestrutura (CI/CD).

- **Prompt 8 (Desenvolvimento da API):** *"Vamo seguir essa linha : vamos terminar os teste e fazer o controller e pois ci/cd"*
  - **Resultado:** A IA guiou a construção da camada RESTful (CarrinhoController), fornecendo o código para expor os métodos do serviço para a internet através das anotações corretas do Spring Boot (@RestController, @GetMapping, @PostMapping).

- **Prompt 9 (Testes de Integração com Cliente HTTP):** *"baixei p postman, o que precisa fazer ? ?"*
  - **Resultado:** A IA elaborou um tutorial prático e passo a passo de como configurar o Postman, ensinando a criar requisições GET e POST (incluindo a formatação do JSON no Body) para testar os endpoints da API rodando no localhost.

- **Prompt 10 (DevOps e CI/CD):** *"agora e hora de fazer o ci/dc e o deploy"*
  - **Resultado:** A IA gerou o arquivo de configuração de pipeline (.yml) para o GitHub Actions, estruturando os jobs necessários para instalar o Java, compilar o código, rodar os testes automaticamente e salvar os artefatos a cada commit.

- **Prompt 11 (Deploy e Contêinerização):** *"estou criando o deploy no render mas não tem a linguaem java"*
  - **Resultado:** A IA identificou a limitação do ambiente Web Service do Render para Java nativo e resolveu o problema instruindo o uso de contêineres, fornecendo um arquivo Dockerfile configurado para compilar e rodar o projeto Spring Boot.

- **Prompt 12 (Configuração de Ambiente):** *"Mas antes de fazer um commite , não preciso configurar o email e o render ?"*
  - **Resultado:** A IA auxiliou na organização do fluxo do Git e explicou o processo de variáveis e portas (PORT) necessárias para que a aplicação se comunicasse corretamente com os servidores externos do Render no momento do deploy.

---

## 👥 Integrantes do Grupo

| Nome | GitHub |
|------|--------|
| Yan Fernandes | [@YanBaptista](https://github.com/YanBaptista) |
| Wagner Dourado | [@Uegner](https://github.com/Uegner) |