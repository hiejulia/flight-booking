package com.project.flightbooking.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import com.project.flightbooking.domain.Payment;
import com.project.flightbooking.domain.PaymentBookingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class PaymentBookingResponseService{

    @Autowired
    private RestTemplate restTemplate;



    @Override
    public PaymentBookingResponse generatePaymentBookingResponse(Payment paymentBooking) {
        PaymentBookingResponse paymentBookingResponse = new PaymentBookingResponse();
        paymentBookingResponse.setPaymentId(paymentBooking.getPaymentId());
        paymentBookingResponse.setBookingId(paymentBooking.getBookingRecord().getBookingId();

        return paymentBookingResponse;
    }

    /**
     * Send PaymentOrderResponse to Food-Order-Service
     */
    @HystrixCommand(fallbackMethod = "sendPaymentOrderResponseFallback")
    @Override
    public void sendPaymentOrderResponse(PaymentBookingResponse paymentBookingResponse) {
        // Eureka Server will find the distribution eureka client, so not need to write server port
        String bookingUrl = "http://booking-service";  // http://[service name]
        this.restTemplate.put(bookingUrl + "/api/order", paymentBookingResponse);

    }

    // Plan B : backup fall back method
    public void sendPaymentBookingResponseFallback(PaymentBookingResponse paymentBookingResponse) {
        log.error("Hystrix Fallback Method. Unable to send PaymentOrderResponse " +
                "to food-order-service");
        // send email to customer


        // update again the booking status and the payment status

    }

}