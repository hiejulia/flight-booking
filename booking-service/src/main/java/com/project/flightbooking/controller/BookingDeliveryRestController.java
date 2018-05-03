package com.project.flightbooking.controller;


import com.project.flightbooking.domain.PaymentBookingResponse;
import com.project.flightbooking.entity.Booking;
import com.project.flightbooking.repository.BookingRepository;
import com.project.flightbooking.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class BookingDeliveryRestController {


    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * When Booking is paid successfully, this api will be called
     * 1. Received PaymentBookingResponse from payment-service
     * 2. Find completed booking in Database
     * 3. Generate BookingDeliveryRequest and send to ticket-delivery service
     *
     * @param paymentBookingResponse
     */
    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    public void paymentBooking(@RequestBody PaymentBookingResponse paymentBookingResponse) {

        // find order in MongoDB & send to delivery service
        // Find booking in database and send to ticket delivery service
//        Order orderCompleted = orderService.findOrder(paymentOrderResponse); // find complete order by paymentbookingresponse
//        Booking bookingCompleted =
//        OrderDeliveryRequest orderDeliveryRequest = orderDeliveryRequestService.generateOrderDeliveryRequest(orderCompleted); // generate order request
//        orderDeliveryRequestService.sendOrderDeliveryRequest(orderDeliveryRequest);// send order deliveryrequst
    }


    @RequestMapping(value = "/bookings", method = RequestMethod.GET)
    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }
}