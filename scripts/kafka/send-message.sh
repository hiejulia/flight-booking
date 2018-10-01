#!/usr/bin/env bash

# Read command line arguments
if [[ $# < 2 ]]; then
    echo "Please use the right command Usage: $0 <topic> <message>"
    exit -1
else
    topic=$1
    message="${*:2}"
fi


curl -X POST \
     -H "Accept: application/json" \
     -H "Content-Type: application/json" \
     -d '{"topic":"'$topic'","message":"'$message'"}' \
     http://localhost:8100/rest/api/v1/messages

echo



