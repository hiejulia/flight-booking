package com.project.flightbooking.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import com.project.flightbooking.domain.BookingDeliveryRequest;
import com.project.flightbooking.entity.Booking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class BookingDeliveryRequestService{

    @Autowired   // Eureka Discovery Client will inject RestTemplate
    private RestTemplate restTemplate;  // Eureka discovery client will inject RestTemplate



    // GenerateBookingDeliveryRequest : param : Booking : function for just generate BookingDeliveryRequest
    public BookingDeliveryRequest generateBookingDeliveryRequest(Booking booking) {
        BookingDeliveryRequest bookingDeliveryRequest = new BookingDeliveryRequest();
        bookingDeliveryRequest.setId(booking.getId());// send
        bookingDeliveryRequest.setPassenger(booking.getPassenger());
        bookingDeliveryRequest.setAirport(booking.getAirportId());
        bookingDeliveryRequest.setFlights(booking.getFlightId());
        return bookingDeliveryRequest;
    }

    /** send OrderDeliveryRequest to Food-Delivery-Service */

    // if sendOrderDeliveryRequest() has error, then call sendOrderDeliveryRequestFallback()
    // Send BookingDeliveryRequest to ticket-delivery service
    @HystrixCommand(fallbackMethod = "sendBookingDeliveryRequestFallback")
    public void sendOrderDeliveryRequest(BookingDeliveryRequest bookingDeliveryRequest) {
        // Eureka server will find the distribution eureka client - so no need to write the server port
        String ticketDelivery = "http://ticket-delivery";  // http://[service name]
        //String foodDelivery = "http://localhost:9003";
        this.restTemplate.postForLocation(ticketDelivery + "/api/delivery", bookingDeliveryRequest);
        log.info("End of sending OrderDeliveryRequest");
    }

    // Plan B : backup fall back method - Hystrix function : for handle if the method is failed
    public void sendBookingDeliveryRequestFallback(BookingDeliveryRequest bookingDeliveryRequest) {
        log.error("Hystrix Fallback Method. Unable to send OrderDeliveryRequest. " +
                "Send a email to notify delivery info");

        // can send a email to notify delivery info - use JavaMailAPI
        // send notificatioon to the admin board
    }
}