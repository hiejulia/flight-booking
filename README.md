# flight-booking
Online flight reservation system 

+ User service : operations on User service 
+ Booking service : Flight service and User service to perform operations on booking. It will use flight search and its associated tabl
+ Flight service : operations and searching based on criteria, providing association between Flight and flight trip 
+ Billing service : operations on billing 


### Stack 
+ Microservice architecture : Spring cloud, Netflix Eureka, 
+ Spring framework 

+ Database : PostgreSQL, MongoDB, Cassandra, 
+ Messaging system : Kafka/ RabbitMQ 
+ Batch process 
+ Apache Avro
+ ElasticSearch - Logstash - Kibana 
+ Container: Docker 

+ REST API testing using Postman




### Endpoint documentation 
+ Flight service
    + GET `v1/flights/id` : get one flight by id 
    + GET `v1/flights` : retrieve all the flights that matches the value of query param 
    + POST `v1/flights` : create new flight 