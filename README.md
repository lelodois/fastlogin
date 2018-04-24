# FastLogin 

É uma api REST que disponibiliza login e logout usando cache para alcançar a melhor performance.

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


## Setup

Instale o redis
docker run -d -p 6379:6379 redis redis-server

Run FastloginApplication.java  ou build do maven

http://localhost:9999/swagger-ui.html

## Startup
É realizado uma carga grande de usuários para uma simples simulação de massa de dados


