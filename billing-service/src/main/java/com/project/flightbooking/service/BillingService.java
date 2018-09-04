package com.project.flightbooking.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BillingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ElectionRepository electionRepository;

    @Autowired
    public ElectionService(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }

    /**
     * Consumes a new election message, deserialize, and save to MongoDB
     *
     * @param electionCreatedMessage
     */
    @RabbitListener(queues = "#{electionCreatedQueue.name}")
    public void getElectionCreatedMessage(String electionCreatedMessage) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        TypeReference<Election> mapType = new TypeReference<Election>() {
        };

        Election election = null;

        try {
            election = objectMapper.readValue(electionCreatedMessage, mapType);
        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }

        electionRepository.save(election);
        logger.debug("Election {} saved to MongoDB", election.toString());
    }


}