package com.project.flightbooking.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voterapi.candidate.domain.Candidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class BillingEventHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RabbitTemplate rabbitTemplate;

    private Queue billingCreatedQueue;

    private Queue billingDeletedQueue;

    private Queue billingUpdatedQueue;

    // Constructor

    @HandleAfterCreate
    public void handleAfterCreated(Candidate candidate) {
        rabbitTemplate.convertAndSend(
                candidateCreatedQueue.getName(), serializeToJson(candidate));
    }

    @HandleAfterSave
    public void handleAfterSaved(Candidate candidate) {
        rabbitTemplate.convertAndSend(
                candidateUpdatedQueue.getName(), serializeToJson(candidate));
    }

    @HandleAfterDelete
    public void handleAfterDeleted(Candidate candidate) {
        rabbitTemplate.convertAndSend(
                candidateDeletedQueue.getName(), serializeToJson(candidate));
    }

    private String serializeToJson(Candidate candidate) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";

        try {
            jsonInString = mapper.writeValueAsString(candidate);
        } catch (JsonProcessingException e) {
            logger.info(String.valueOf(e));
        }

        logger.debug("Serialized message payload: {}", jsonInString);

        return jsonInString;
    }
}