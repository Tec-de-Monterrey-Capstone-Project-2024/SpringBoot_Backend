#!/bin/sh

echo "PORT -> ${PORT}"
echo "DB_HOST -> ${DB_HOST}"
echo "DB_NAME -> ${DB_NAME}"
echo "DB_USER -> ${DB_USER}"
echo "DB_PASSWORD -> ${DB_PASSWORD}"
echo "AWS_ACCESS_KEY -> ${AWS_ACCESS_KEY}"
echo "AWS_SECRET_KEY -> ${AWS_SECRET_KEY}"
echo "AWS_REGION -> ${AWS_REGION}"

java -DPORT="${PORT}" \
  -DDB_HOST="${DB_HOST}" \
  -DDB_NAME="${DB_NAME}" \
  -DDB_USER="${DB_USER}" \
  -DDB_PASSWORD="${DB_PASSWORD}" \
  -DAWS_ACCESS_KEY="${AWS_ACCESS_KEY}" \
  -DAWS_SECRET_KEY="${AWS_SECRET_KEY}" \
  -DAWS_REGION="${AWS_REGION}" \
  -jar /app/springboot-connectmate-rest-api.jar