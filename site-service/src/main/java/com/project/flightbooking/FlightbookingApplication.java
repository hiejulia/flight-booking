package com.project.flightbooking;

import com.project.flightbooking.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
//@EnableGlobalMethodSecurity
public class FlightbookingApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(FlightbookingApplication.class);

	RestTemplate searchClient = new RestTemplate();

	RestTemplate bookingClient = new RestTemplate();

	RestTemplate checkInClient = new RestTemplate();

	RestTemplate restClient = new RestTemplate();

	@Autowired
	private Environment env;
	public static void main(String[] args) {
		SpringApplication.run(FlightbookingApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		// Search for a flight
		SearchQuery searchQuery = new SearchQuery("BLR", "CHE", "20-JAN-17");
		String searchUrl = env.getProperty("custom.flight.search.url");
		Flight[] flights = searchClient.postForObject(searchUrl + "/get", searchQuery, Flight[].class);

		Arrays.asList(flights).forEach(flight -> logger.info(" flight >" + flight));

		// create a booking only if there are flights.
		if (flights == null || flights.length == 0) {
			return;
		}
		Flight flight = flights[0];
		BookingRecord booking = new BookingRecord(flight.getFlightNumber(), flight.getOrigin(), flight.getDestination(),
				flight.getFlightDate(), null, flight.getFares().getFare());
		Set<Passenger> passengers = new HashSet<Passenger>();
		passengers.add(new Passenger("Tony", "Stark", "Male", booking));
		booking.setPassengers(passengers);
		long bookingId = 0;
		try {
			String bookingUrl = env.getProperty("custom.flight.booking.url");
			bookingId = bookingClient.postForObject(bookingUrl + "/create", booking, long.class);
			logger.info("Booking created " + bookingId);
		} catch (Exception e) {
			logger.error("BOOKING SERVICE NOT AVAILABLE...!!!");
		}

		// check in passenger
		if (bookingId == 0)
			return;
		try {
			CheckInRecord checkIn = new CheckInRecord("Tony", "Stark", "28C", null, "BF101", "20-JAN-17", bookingId);
			String checkinUrl = env.getProperty("custom.flight.checkin.url");
			long checkinId = checkInClient.postForObject(checkinUrl + "/create", checkIn, long.class);
			logger.info("Checked IN " + checkinId);
		} catch (Exception e) {
			logger.error("CHECK IN SERVICE NOT AVAILABLE...!!!");
		}
	}
}
