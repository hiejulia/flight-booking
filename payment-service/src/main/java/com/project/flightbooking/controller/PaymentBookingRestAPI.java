package com.project.flightbooking.controller;



import com.project.flightbooking.domain.Payment;
import com.project.flightbooking.domain.PaymentBookingResponse;
import com.project.flightbooking.service.PaymentBookingResponseService;
import com.project.flightbooking.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentBookingRestAPI {


    private static final String PAYMENT_STATUS_SUCCESS = "Success";

    private PaymentService paymentService;
    private PaymentBookingResponseService paymentBookingResponseService;

    @Autowired
    public PaymentBookingRestAPI(PaymentService paymentService,
                                   PaymentBookingResponseService paymentBookingResponseService) {
        this.paymentService = paymentService;
        this.paymentBookingResponseService = paymentBookingResponseService;
    }

    /**
     * 1.  Receive paymentOrder from third-party-payment-service
     * 2.  Send paymentOrderResponse to food-order-service
     * 3.  Update PaymentInfo [MySQL]
     */
    @RequestMapping(value = "/api/payment", method = RequestMethod.PUT)
    public void paymentBooking(@RequestBody Payment paymentBooking) {


        // Send message to Booking service
        if (paymentBooking.getPaymentStatus().equals(PAYMENT_STATUS_SUCCESS)) {
            // generate payment booking response
            PaymentBookingResponse paymentOrderResponse = paymentBookingResponseService.generatePaymentBookingResponse(paymentBooking);
            paymentBookingResponseService.sendPaymentOrderResponse(paymentOrderResponse);
        }

        // Mark this payment as completed
        paymentService.updatePaymentStatusById(paymentBooking.getPaymentStatus(),
                paymentBooking.getPaymentId());
    }


}