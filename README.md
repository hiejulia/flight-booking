# flight-booking
Online flight reservation system 

+ User service : operations on User service 
+ Booking service : Flight service and User service to perform operations on booking. It will use flight search and its associated tabl
+ Flight service : operations and searching based on criteria, providing association between Flight and flight trip 
+ Billing service : operations on billing 


### Stack 
+ Microservice architecture : Spring cloud, Netflix Eureka, Ribbon, Zuul, Hystrix, Service discovery, Load Balancing, API gateway, Circuit breaker 
+ Spring framework 

+ Database : PostgreSQL, MongoDB, Cassandra, 
+ Messaging system : Kafka/ RabbitMQ 
+ Batch process 
+ Apache Avro
+ ElasticSearch - Logstash - Kibana 
+ Container: Docker 

+ REST API testing using Postman
+ Testing : JUnit, E2E test with Cucumber
+ Event - driven system 



### Endpoint documentation 
+ Flight service
    + GET `v1/flights/id` : get one flight by id 
    + GET `v1/flights` : retrieve all the flights that matches the value of query param 
    + POST `v1/flights` : create new flight 





### Run the project 

java -jar eureka-service/target/eureka-service.jar
java -jar fligth-service/target/flight-service.jar
java -jar booking-service/target/booking-service.jar
java -jar user-service/target/user-service.jar