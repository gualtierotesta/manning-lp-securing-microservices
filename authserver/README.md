# Authentication Server

Implement an OAuth 2 authorization server that issues tokens signed with the private key of an asymmetric key pair.

This authorization server uses an in-memory managed client and user.

## How to generate the private key

    keytool -genkeypair -alias gte -keyalg RSA -keypass gte123 -keystore c.jks -storepass gte123
    mv gte.jks src/main/resources/gte.jks

## How to generate the public key

    keytool -list -rfc --keystore src/main/resources/gte.jks | openssl x509 -inform pem -pubkey
