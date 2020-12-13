DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    username  VARCHAR(64) NOT NULL,
    password  VARCHAR(250),
    authority VARCHAR(64)
);


DROP TABLE IF EXISTS clients;

CREATE TABLE clients
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    client_id   VARCHAR(64) UNIQUE NOT NULL,
    secret      VARCHAR(64)        NOT NULL,
    grant_types VARCHAR(250)       NOT NULL,
    scopes      VARCHAR(64)        NOT NULL
);
