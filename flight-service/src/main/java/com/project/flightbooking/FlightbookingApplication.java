package com.project.flightbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class FlightbookingApplication {
	private static final Logger LOG = LoggerFactory.getLogger(FlightbookingApplication.class);

	@Value("${app.rabbitmq.host:localhost}")
	String rabbitMqHost;

	@Bean
	public ConnectionFactory connectionFactory() {
		LOG.info("Create RabbitMqCF for host: {}", rabbitMqHost);
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitMqHost);
		return connectionFactory.getRabbitConnectionFactory();
	}


	public static void main(String[] args) {
		SpringApplication.run(FlightbookingApplication.class, args);
	}
}
