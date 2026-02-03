# Projeto Spring Boot – Estudos de Testes Automatizados

## Sobre o Projeto
Este projeto foi desenvolvido com o objetivo de estudar e aplicar **testes automatizados em aplicações Spring Boot**, abordando diferentes níveis de testes e ferramentas utilizadas no mercado.

O foco principal foi garantir a qualidade do código por meio de **testes unitários, testes de integração, testes funcionais** e análise de **cobertura de testes com JaCoCo**.

---

## Objetivos
- Praticar testes automatizados em aplicações Spring Boot  
- Entender as diferenças entre testes unitários, de integração e funcionais  
- Utilizar banco de dados em memória (**H2**) para testes  
- Medir e analisar cobertura de código com **JaCoCo**  

---

## Tecnologias Utilizadas
- Java  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- H2 Database  
- JUnit 5  
- Mockito  
- MockMvc  
- JaCoCo  
- Maven  

---

## Tipos de Testes

### Testes Unitários
- Testes focados na regra de negócio  
- Isolamento das dependências com Mockito  
- Validação do comportamento dos métodos da camada de serviço  

### Testes de Integração 
- Uso do banco H2 em memória  
- Testes sem mocks, simulando o ambiente real da aplicação  

### Testes Funcionais
- Simulação de requisições HTTP  
- Uso do MockMvc  
- Validação de status HTTP, respostas e fluxo completo da aplicação  

---

## Cobertura de Testes
A cobertura de testes é gerada utilizando o **JaCoCo**.

Após a execução dos testes, o relatório pode ser acessado em: **target/site/jacoco/index.html**

