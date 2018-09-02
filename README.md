# flight-booking
Online flight reservation system 

+ User service : operations on User service 
    + Database : 
+ Booking service : Flight service and User service to perform operations on booking. It will use flight search and its associated table
    + Database :
+ Flight service : operations and searching based on criteria, providing association between Flight and flight trip 
    + Database : MySQL
+ Billing service : operations on billing 
    + Database : MongoDB
+ Passenger service 
    + Database : Cassandra 


+ Booking-client 
    
+ Auth-server : User / Passenger need to register/ login - authorized 
+ Twitter Service : read twitter streams(from Airline branch twitter) and persist them on MongoDB and served them as a REST API(HATEOAS) to other service
+ Search service : Read tweets from twitter service and push it on ElasticSearch as a search engine and produce userful search for these tweet
+ social-network-service : send flights and passengers data to Agency social network 
    + Database : Neo4J

### Stack 
+ Applied to the principles of the 12 Factor App
+ Microservice architecture : Spring cloud, Netflix Eureka, Ribbon, Zuul, Hystrix, Service discovery, Load Balancing, API gateway, Circuit breaker (Hystrix)
+ Spring framework : Spring Boot, Spring cloud, Spring data, Spring Stream , Spring Reactor
+ CQRS - Event sourcing 
+ Database : PostgreSQL, MongoDB, Cassandra, MySQL - MariaDB, Neo4J , MySQL
+ Caching : Redis
+ Messaging system : RabbitMQ 
+ Batch process 
+ Apache Avro
+ ElasticSearch - Logstash - Kibana 
    + ES GUI plugin 
    + Install Logstash 
        + Config logstash 
    + Install Kibana 
        + localhost:5601
    + Docker image ELK stack from Docker hub 
+ Container: Docker  - Docker compose 
+ REST API testing using Postman
+ Testing : JUnit, E2E test with Cucumber
    + JUnit, Mockito, WireMock 
+ Event - driven system 
+ Security : OAuth/ JWT 
+ Log analysis : ELK stack - Logstash - ElasticSearch - Kibana to index logs 
    + ElasticSearch : distributed, JSON based search and analytics engine designed for horizontal scalability, maximum reliability , easy management 
    + Logstash : dynamic data collection pipeline with an extension plugin ecosystem and strong elasticsearch synergy
    + Kibana : visualization of data though UI 
    + ELK stack architecture 
        + User view logs from Kibana which is the user interface of elasticsearch cluster -> logstash will listen the application logs and transform those to json format and send to elasticsearch 
    + Distributed tracing and centralized log management
        + Spring Cloud sleuth & Zipkin 
        + Config Kibanan and view the logs 
+ API doc : Swagger 
+ Service Logging / Monitoring 



### Endpoint documentation 
+ Flight service
    + GET `v1/flights/id` : get one flight by id / info 
    + GET `v1/flights` : retrieve all the flights that matches the value of query param 
    + POST `v1/flights` : create new flight 
    + GET `v1/airports` : get a list of airports
    + GET `v1/airports/{airport-name}` : list of flights from this airport 
    + Search flight by name
    

+ Booking service : User can book a flight ticket and fill the personal information - billing information 
    + POST `v1/flights/{flight-id}/booking ` : 
    + Get booking details 

+ Billing service : User can pay the flight order 
    + GET `v1/flights/{flight-id}/payment`
    + Make payment(handle payment errors: payment authorization timeout and invalid credit card info )

+ User can unsubscribe to the flight ticket info
    + GET `v1/flights/{flight-id}/payment/ubsubscribed`




### Run the project 

+ Run everything : 



+ `docker-compose up` : RabbitMQ port set up (in the docker folder) : it will start the RabbitMQ and MongoDB instance 

java -jar eureka-server/target/eureka-server.jar  : Start Eureke server  -  Eureka server port set up
java -jar dashboard-server/target/dashboard-server.jar : Start Hystrix dashboard - Hystrix port set up 
java -jar turbine-server/target/turbine-server.jar 

java -jar flight-service/target/restaurant-service.jar 
java -jar user-service/target/user-service.jar 
java -jar booking-service/target/booking-service.jar 
java -jar api-service/target/api-service.jar

+ Before start Zuul service, make sure that all of the services are up in the Eureka dashboad : `localhost:8761`
java -jar zuul-server/target/zuul-server.jar 



+ How to double check all components are setting up 
1. Browser : Eureka server 
`http://localhost:8761/`
Service instances are register with Eureka 


2. Browser : Hystrix monitor : `http://localhost:7979/ ` - Hystrix dashboard 
+ Substitude : [http://hostname:port/turbine/turbine.stream] to
                    URL:  http://localhost:9001/hystrix.stream
         --> Monitor Stream

3. Browser : RabbitMQ management : `http://localhost:15672/` (guest- guest )



+ Install and run 
    + MongoDB : `docker run --name mongo -p 27017:27017 -d mongo`
    + Redis : `dockewr run --name redis -p 6379:6379 -d redis`

### Microservice architecture 
+ Service discovery and registration - Eureka 
    + Run multiple instances of the same microservices
    + Look up the host name and the port number by checking discovery service 
    + Eureka server : `http://localhost:8761/`
    + Advanced discovery client config 
    + Enable secure communication between client and server
    + Config failover and peer - to - peer replication mechanism 
    + Register instances of client - side application in different zones 
    + Cluster env : zone mechanism - enable zone handling 
    + Replication and high availability - cluster mechanism - peer to peer replication model - replication mechanism 
    + Run instances of Eureka 
    + Enable secure communication between client and server 
        + Register a secure service 
        + Enable SSL by generating a self - signed certificate 
        + SSL is enabled for edge-service only 

+ Config service   
    + Application configuration to all the other microservices 
    + Port number, context paths 
    + Configuration - using GIT 

+ Edge or proxy server (API gateway) - Zuul - Gateway service 
    + Gateway service Zuul 
    + Proxy all calls to the target microservice 
    + Solve CORS request - Enable CORS headers on the proxy only
    + Zuul integrates with Eureka (discovery-service)
    + Gateway service that provides dynamic routing, monitor, resiliency, security 
+ Load balancing : Ribbon is used for load balancing . It is integrated with the Zuul and Eureka services to provide load balancing for both internal and external calls 
    + Server side load balancing : Zuul server as edge server 
    + Client side load balancing : Ribbon - FeignClient 
+ Circuit breaker : Netflix hystrix 
+ Distributed tracing : Zipkin, Spring Cloud Sleuth - distributed tracing via logs - distributed tracing system with request visualization 
    + tracing mechanism 
    + trace and span ID 
    + record time - latency analysis - statistics = zipkin = query and visualize data 
    + stream = producer of message sent to message broker 
+ Monitoring : Netflix Turbine  - and Dashboard 
    + Hystrix provides a dashboard UI `locahost:7979`
    + Turbine stream `http://localhost:8989/turbine.stream`
    + Hystrix uses RabbitMQ to send metrics data feed to Turbine 
    + Config and collect metrics and tracing from all services
+ UUA servvice 
    + User account & Authentication - security of the application 
    + /token endpoint to retrieve a token 
    + /user endpoint to validate a token and retrieve the user and its roles
    + Token in this case are long-lived 

+ Integration
+ Batch processing 
+ Security service 
    + Secure microservices architecture 
    + SSL enabled 

+ Dependency management : Maven 
+ Data Lake 
+ Testing 
+ Event sourcing 
+ HTTP listener 
+ Containers / Virtual Machines 
+ Cluster Control and Provisioning 

+ Admin server 

+ Spring cloud DataFlow server 
    + `dataflow-service` is a Spring Boot app that loads the local DataFlow server. Port 9393
    + To interact with the dataflow server, you can donw 


+ Microservice service design : contract design and protocol selection 
    + Contract design 
        + Simplicity - consumer 
        + KISS 
        + Consumer driven contract 
    + Protocol selection 
        + HTTP/ SOAP 
        + Messaging 
    + Message oriented service 
        + JMS / AMQP protocol - JSON 
        + Messaging over HTTP 
        + Async REST 
    + HTTP and REST endpoint 
        + Protocol handling 
        + Traffic routing 
        + Load balancing 
        + Security systems 
        + HATEOAS
    + Optimize communication protocol - for communication between service 
        + Avro 
        + Protocol Buffers 
        + Apache Thrift 
        + Custom binary protocol 
        + RPC 
+ API documentation : Swagger, RAML, API blueprint 

+ ELK stack 
    + ELK config - Use Docker container to run the ELK stack 
    1. Run this command on Docker terminal `docker run -d -it --name es -p 9200:9200 -p 9300:9300 -e ES_JAVA_OPTS="-Xms1g -Xmx1g" -m 1500m elasticsearch` : start ElasticSearch container on 9200/9300 port 
    2. `docker run -d -it --name kibanak --link es:elasticsearch -p 5601:5601 kibana` : start Kibana on 5601 port and it will also link it with ElasticSearch container 
    3. `docker run -d -it --name logstash -p 5000:5000 logstash -e 'input { tcp { port => 5000 codec => "json" } } output { elasticsearch { hosts => ["192.168.99.100"] index => "micro-%{serviceName}"} }'` : start Logstash container on 5000 port and also create an index with name micro-*
    4. Checking with `docker ps` command, all the container should be running 
    + Default port used for docker container is 192.168.99.100 

    + Kibana : check logs on Kibana 
        + With `Log.info` statement and `logback.xml` configuration we can index and view log from Kibana 


+ Zipkin server 
    + Check the log traces on zipkin server `localhost:9411`
    + Contain Spring Zipkin Stream server 

+ Docker 
    + Docker containers in this microservice group - Mongo - RabbitMQ - Config-service - Discovery - service , Gateway- service, Command-service, Query-service 
    + Using docker- compose, run : `docker-compose -f docker-compose.yml up`
    + To see the running containers `docker ps`

    + Run MongoDB and RabbitMQ 
        + `docker-compose up -d mongodb rabbitmq`





+ Jenkins 
    + Continuous deploy using Jenkins Pipeline 
        + Create docker image to have CD 
            + Image contains : build project, create docker images, deploy on AWS using ECS container
            + Using Jenkinsfile - config Job on Jenkins using Pipeline plugin and paste the content of Jenkins file in the Pipeline script box 


+ Deploy on AWS
    + Create credentials on AWS 
    + Create cluster on AWS 
    + Build deploy container 
    + Access Jenkins panel 
    + Create a pipeline job 
    + Run the job 


+ Scaling 
    + NGINX will be configured for browser caching of the static content and load balancer - scale App Gateway and update manually the ports in default.conf - upstream config section 







### Project architecture
+ Flight service    
    + Database : MongoDB 
    + User search for flight based on search query 
+ Catalog service 

+ User service - Account service 
    + User/ Customer register account
    + Database : MySQL 
+ Account service 
+ Booking service 
    + Database : MongoDB 
    + User book the flight and fill needed information for the flight 
+ Billing service 
    + Dabase : RDBMS 
    + User pay for flight ticket 

+ Subscription service 
    + Use
+ Route service 
    + User search for flight by flight route and flight city
+ Notification service 
    + Notification is send to user when user book a flight 
+ Ticker order service : before book for ticker, user can choose to order (reserve the flight ticker in a certain of time )
+ Payment service : After sending bill to user(customer), payment service is used for pay the fee of the flight ticket 
+ third party payment service 

+ Credit risk engine :  
    + Service to test for the validity of the bank account 
    










### Other 
#### How to deploy in AWS 
+ Create folder for each microservice(except flightsearchclient) project in AWS AMI root folder(e.g "eureka")
+ Copy the corresponding jar and the dockerfile in that folder (e.g "eurekaserver-0.0.1-SNAPSHOT.jar" and "Dockerfile" )
+ Go to that directory from root and run docker build command to create docker image("cd eureka","sudo docker build -t eureka .")
+ Go to root directory and run docker compose command("sudo docker-compose up")
+ To stop run "sudo docker-compose down"










