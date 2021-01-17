curl -v -X POST "http://localhost:5050/api/test/data" \
    -H "Content-Type: application/json" \
    -d '
[
  {
    "username": "user1",
    "healthMetricType": "ECG",
    "value": 123.45
  },
  {
    "username": "user1",
    "healthMetricType": "HEART_RATE",
    "value": 80.1
  }
]'

echo ''
