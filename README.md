# manning-lp-securing-microservices

Homework for Manning LiveProject on "Securing Microservices with Spring Security and OAuth2"

## authserver

This project is the OAuth authorization server

    URL: http://localhost:8080
    Storage based on in-memory H2 db. 
    Spring Boot 2.3.x
    Java 11

## businessserver

This project is the OAuth **resource** server which contains the business logic.

    URL: http://localhost:7070
    Storage based on in-memory H2 db. 
    Spring Boot 2.3.x 
    Java 11

OAuth tokens are JWT, and they are validated using shared asymmetric key.
