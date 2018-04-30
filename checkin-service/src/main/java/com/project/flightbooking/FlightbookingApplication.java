package com.project.flightbooking;

import com.project.flightbooking.entity.CheckInRecord;
import com.project.flightbooking.repository.CheckinRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FlightbookingApplication implements CommandLineRunner{
	private static final Logger logger = LoggerFactory.getLogger(FlightbookingApplication.class);

	@Autowired
	CheckinRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(FlightbookingApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		// Add sample data to checkin
		CheckInRecord record = new CheckInRecord("Tony", "Stark", "28A", new Date(), "BF101", "20-JAN-17", 1);

		CheckInRecord result = repository.save(record);
		logger.info("checked in successfully ..." + result);

		logger.info("Looking to load checkedIn record...");
		logger.info("Result: " + repository.getOne(result.getId()));

	}


}
