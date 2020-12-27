clear

## GRANT TYPE: PASSWORD
export TOKEN=$(curl -s -XPOST -u client-p:secret 'http://localhost:8080/oauth/token' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d 'grant_type=password&username=john&password=12345&scope=read' | jq -r ."access_token")
echo $TOKEN
echo ""
echo "------------------------------------------------------------------"
echo ""

#curl -v -X POST 'http://localhost:7070/profile' \
#  -H "Authorization: Bearer ${TOKEN}" \
#  -H "Content-Type: application/json" \
#  -d '{"username": "jane"}'

echo ""
echo "------------------------------------------------------------------"
echo ""

curl -X GET 'http://localhost:7070/profile/jane' \
  -H "Authorization: Bearer ${TOKEN}"

echo ""
