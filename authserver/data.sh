clear

## OAUTH Clients

## Password grant client
curl -v -X POST 'http://localhost:8080/api/clients' \
    -H 'Content-Type: application/json' \
    -d '{"clientId": "client-p", "secret": "secret", "grantTypes": "password,refresh_token", "scopes":"read"}'

## Authorization code client
curl -v -X POST 'http://localhost:8080/api/clients' \
    -H 'Content-Type: application/json' \
    -d '{"clientId": "client-ac", "secret": "secret", "grantTypes": "code,refresh_token", "scopes":"read", "redirectUris":"http://localhost:8080/home"}'

## Client credentials client
curl -v -X POST 'http://localhost:8080/api/clients' \
    -H 'Content-Type: application/json' \
    -d '{"clientId": "client-c", "secret": "secret", "grantTypes": "client_credentials", "scopes":"info"}'

curl -v 'http://localhost:8080/api/clients'

## Create Users
curl -v -XPOST 'http://localhost:8080/api/users' \
    -H 'Content-Type: application/json' \
    -d '{"username": "john", "password": "12345", "authority": "read"}'

## List Users
curl -v 'http://localhost:8080/api/users'
