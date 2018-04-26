# FastLogin 

É uma solução para o problema de login concorrente e com grande massa de dados.

[Problema]
 - Grande concorrência de usuários acessando mesmas consultas para validação de login;

Passos da solução

 - Banco de dados em memória como um cache da sessão;
 - Sessão disponível para rápido acesso e com expiração automática;
 - Failback no banco de dados relacional;
 - Replicação do acesso autorizado em memória;
 - Api restfull e conceito de token da sessão.
 

## Arquitetura

* RestAPI
* Java 8
* Maven
* Spring boot 2.0
* Tomcat embedded
* Hibernate
* Swagger2
* H2
* Redis
* Junit RestTemplate


## Endpoints

|HTTP Request                                            |
|--------------------------------------------------------|
|POST /login/  body:LoginMessage
|GET  /login/status/{login}                              | 
|GET  /login/sair                                        |


# Swagger
http://localhost:9999/swagger-ui.html


## Setup

 - [Instale o docker]     - https://docs.docker.com/install/
 - [Instale o redis]      - docker run -d -p 6379:6379 redis redis-server
 - [Execute a aplicação]  - Run FastloginApplication.java  ou build do maven
 - [Execute os endpoints] - http://localhost:9999/swagger-ui.html

## Observação
  
 * É realizado uma carga grande de usuários para uma simples simulação de massa de dados.
 * Para execução dos testes (e da app) unitários de integração, é necessário o  **redis executando**

