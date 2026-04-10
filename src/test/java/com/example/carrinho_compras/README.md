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
- **Testes de Integração/Web (`Controller`):** Utilizando `@WebMvcTest`, simulam requisições HTTP reais para garantir que todas as rotas respondem corretamente com os status HTTP esperados (`200 OK`, `400 Bad Request`, etc.).

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

Nesta seção, declaramos como a IA (**Gemini**) foi integrada ao ciclo de desenvolvimento prático para elevar a qualidade do software, superar desafios técnicos do Git e garantir a robustez das regras de negócio.

### 📝 Prompts Utilizados e Resultados Obtidos

- **Prompt 1 (Troubleshooting de DevOps):** *"Como resolver erro 403 Permission Denied ao fazer push no IntelliJ? A branch falha ao empurrar para o origin."*
    - **Resultado:** A IA guiou a limpeza do Gerenciador de Credenciais do Windows e a execução de um push via terminal (`git push -u origin`), resolvendo o travamento de credenciais antigas e permitindo a entrega do código no repositório remoto.

- **Prompt 2 (Engenharia de Testes e TDD):** *"Preciso de testes inoportunos para um Carrinho de Compras em Java. O que acontece se o produto for nulo ou sem nome?"*
    - **Resultado:** Utilizando a abordagem *Test-Driven Development (TDD)*, os testes inicialmente falharam. Com a orientação da IA, refatoramos o `CarrinhoService.java` para incluir o lançamento de `IllegalArgumentException` em casos de produtos nulos ou com strings vazias, blindando a API e fazendo a suíte de 20 testes passar com sucesso.

- **Prompt 3 (Pipeline CI/CD):** *"Como estruturar um pipeline no GitHub Actions com jobs paralelos para Spring Boot?"*
    - **Resultado:** Auxílio na escrita do `.yml`, garantindo a criação de jobs distintos para `test` e `build`, otimizando o tempo de execução e configurando o salvamento de Artifacts (o arquivo `.jar`).

---

## 👥 Integrantes do Grupo

| Nome | GitHub |
|------|--------|
| Yan Fernandes | [@YanBaptista](https://github.com/YanBaptista) |
| Wagner Dourado | [@Uegner](https://github.com/Uegner) |