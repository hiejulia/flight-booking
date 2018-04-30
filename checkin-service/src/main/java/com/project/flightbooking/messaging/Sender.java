package com.project.flightbooking.messaging;



import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    // Config RabbitMQ
    RabbitMessagingTemplate template;

    @Autowired
    Sender(RabbitMessagingTemplate template) {
        this.template = template;
    }

    @Bean
    Queue queue() {
        return new Queue("CheckINQ", false);
    }
    // Send message to CheckINQ
    public void send(Object message) {
        template.convertAndSend("CheckINQ", message);
    }
}