package com.project.flightbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHystrix
@EnableEurekaClient
//@EnableBinding(EventMessageChannels.class)
@SpringBootApplication
public class FlightbookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightbookingApplication.class, args);
	}
}
