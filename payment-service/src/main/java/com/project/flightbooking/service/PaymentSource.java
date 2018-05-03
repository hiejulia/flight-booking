package com.project.flightbooking.service;

import demo.domain.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

@EnableBinding(Source.class) // Bind MQ output channel with this class
@Slf4j  // for log
public class PaymentSource {

    @Autowired // Inject MQ output channel
    private MessageChannel output;

    public String sendMessageToOutputChannel(Payment payment) {
        log.info("PaymentOrder @food-payment-service " + payment.toString());
        this.output.send(MessageBuilder.withPayload(payment).build()); // send message thru output channel
        return "Payment Pending";
    }

}



// Change this to use RabbitMQ