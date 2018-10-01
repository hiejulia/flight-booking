#!/usr/bin/env bash

echo "List kafka message"

curl -X GET http://localhost:8100/rest/api/v1/topics
echo
