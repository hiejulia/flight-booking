package com.project.flightbooking.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BillingConfig {

    @Bean
    public Queue candidateCreatedQueue() {
        return new Queue("candidate_created_queue");
    }

    @Bean
    public Queue candidateUpdatedQueue() {
        return new Queue("candidate_updated_queue");
    }

    @Bean
    public Queue candidateDeletedQueue() {
        return new Queue("candidate_deleted_queue");
    }

    @Bean
    public Queue electionCreatedQueue() {
        return new Queue("election_created_queue");
    }

    @Bean
    public Queue electionUpdatedQueue() {
        return new Queue("election_updated_queue");
    }

    @Bean
    public Queue electionDeletedQueue() {
        return new Queue("election_deleted_queue");
    }
}