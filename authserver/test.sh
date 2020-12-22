clear

## GRANT TYPE: PASSWORD
curl -v -XPOST -u client-p:secret 'http://localhost:8080/oauth/token' \
    -H 'Content-Type: application/x-www-form-urlencoded' \
    -d 'grant_type=password&username=john&password=12345&scope=read'

## GRANT TYPE: authorization_code
#curl -v -XPOST -u client-ac:secret \
#    'http://localhost:8080/oauth/token?grant_type=code&username=john&password=12345&scope=read'

#curl -v -XGET 'http://localhost:8080/oauth/authorize?response_type=code&client_id=client-ac&scope=read'

## GRANT TYPE: client credentials
curl -v -X POST -u client-c:secret 'http://localhost:8080/oauth/token' \
    -H 'Content-Type: application/x-www-form-urlencoded' \
    -d 'grant_type=client_credentials'
