DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    username  VARCHAR(64) NOT NULL,
    password  VARCHAR(250),
    authority VARCHAR(64)
);
