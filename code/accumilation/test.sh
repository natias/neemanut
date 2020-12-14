curl -X GET "http://localhost:8080/ctrl/load-kartisim" -H  "accept: */*"
curl -X GET "http://localhost:8080/ctrl/load-tnuout" -H  "accept: */*"

curl -X POST "http://localhost:8080/accounts" -H  "accept: */*" -H  "Content-Type: application/json" -d "{  \"accountDetails\": {    \"bank\": \"52\",    \"mch\": \"5040\",    \"snif\": \"168\"  },  \"genericHeader\": {    \"clientId\": \"1\",    \"requestId\": \"string\",    \"timeStamp\": \"2020-12-14T10:58:51.734Z\"  }}" |jq "."
