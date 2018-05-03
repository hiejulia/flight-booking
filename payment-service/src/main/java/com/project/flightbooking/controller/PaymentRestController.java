package com.project.flightbooking.controller;



import com.project.flightbooking.domain.Payment;
import com.project.flightbooking.domain.Sender;
import com.project.flightbooking.service.PaymentService;
import com.project.flightbooking.service.PaymentSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
public class FoodPaymentRestController {

    private PaymentService paymentService;
    private PaymentSource paymentSource;  // MQ Source

    @Autowired
    public FoodPaymentRestController(PaymentService paymentService, PaymentSource paymentSource) {
        this.paymentService = paymentService;
        this.paymentSource = paymentSource;
    }

    /**
     * get sender / passenger information
     * @param userName
     * @return
     */
    // localhost:9001/payment/Angela
    @RequestMapping(value = "/payment/{userName}", method = RequestMethod.GET)
    public Sender findSenderInfo(@PathVariable String userName) {
        return paymentService.findBySenderUsername(userName);
    }

    /**
     * PAY MAIN SERVICE
     * @param payment
     * @return
     */
    // localhost:9001/payment
    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String savePaymentBooking(@RequestBody Payment payment) {
        // save payment booking
        Payment paymentBooking = paymentService.savePaymentBooking(payment);
        // send message to queue -> payment has been created
        return paymentSource.sendMessageToOutputChannel(paymentBooking);
//        return paymentService.updatePaymentStatusById("pending", paymentOrder.getPaymentId());
    }

}