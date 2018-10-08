#!/usr/bin/env bash


# Run kibana 
bin/kibana agent -f logstash.conf



# Get request 

curl  http://localhost:5601/
