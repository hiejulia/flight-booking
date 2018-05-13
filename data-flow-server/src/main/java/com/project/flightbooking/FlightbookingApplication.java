package com.project.flightbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.dataflow.server.EnableDataFlowServer;

@SpringBootApplication
@EnableDataFlowServer // Enable data flow server 
public class FlightbookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightbookingApplication.class, args);
	}
}
