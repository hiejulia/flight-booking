# flight-booking
Online flight reservation system 

+ User service : operations on User service 
+ Booking service : Flight service and User service to perform operations on booking. It will use flight search and its associated tabl
+ Flight service : operations and searching based on criteria, providing association between Flight and flight trip 
+ Billing service : operations on billing 


### Stack 
+ Microservice architecture : Spring cloud, Netflix Eureka, Ribbon, Zuul, Hystrix, Service discovery, Load Balancing, API gateway, Circuit breaker 
+ Spring framework : Spring Boot, Spring cloud, Spring data, Spring Stream , Spring Reactor
+ CQRS - Event sourcing 
+ Database : PostgreSQL, MongoDB, Cassandra, MySQL, Neo4J , 
+ Caching : Redis, 
+ Messaging system : Apache Kafka/ RabbitMQ 
+ Batch process 
+ Apache Avro
+ ElasticSearch - Logstash - Kibana 
+ Container: Docker 

+ REST API testing using Postman
+ Testing : JUnit, E2E test with Cucumber
+ Event - driven system 
+ Security 
+ Log analysis : ELK stack


### Endpoint documentation 
+ Flight service
    + GET `v1/flights/id` : get one flight by id 
    + GET `v1/flights` : retrieve all the flights that matches the value of query param 
    + POST `v1/flights` : create new flight 





### Run the project 

java -jar eureka-server/target/eureka-server.jar 
java -jar turbine-server/target/turbine-server.jar 
java -jar dashboard-server/target/dashboard-server.jar 
java -jar flight-service/target/restaurant-service.jar 
java -jar user-service/target/user-service.jar 
java -jar booking-service/target/booking-service.jar 
java -jar api-service/target/api-service.jar

+ Before start Zuul service, make sure that all of the services are up in the Eureka dashboad : `localhost:8761`
java -jar zuul-server/target/zuul-server.jar 





### Microservice architecture 
+ Service discovery and registration 
+ Edge or proxy server (API gateway) - Zuul 
+ Load balancing : Ribbon is used for load balancing . It is integrated with the Zuul and Eureka services to provide load balancing for both internal and external calls 
    + Server side load balancing : Zuul server as edge server 
    + Client side load balancing : Ribbon - FeignClient 
+ Circuit breaker : Netflix hystrix 
+ Monitoring : Netflix Turbine 
    + Hystrix provides a dashboard UI `locahost:7979`
    + Turbine stream `http://localhost:8989/turbine.stream`
    + Hystrix uses RabbitMQ to send metrics data feed to Turbine 
+ Eureka server : `http://localhost:8761/`

+ Security: 
    + Secure microservices architecture 
    + SSL enabled 








### Project architecture
+ Flight service    
    + User search for 
+ Catalog service 

+ User service - Account service 
    + User/ Customer register account
    + Database : MySQL 
+ Account service 
+ Booking service 
+ Billing service 
+ Route service 
    + User search for flight by flight route and flight city
+ Notification service 
    + Notification is send to user when user book a flight 
+ Ticker order service : before book for ticker, user can choose to order (reserve the flight ticker in a certain of time )
+ Payment service : After sending bill to user(customer), payment service is used for pay the fee of the flight ticket 
+ third party payment service 