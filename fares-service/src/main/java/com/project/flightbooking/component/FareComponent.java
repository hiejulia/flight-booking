package com.project.flightbooking.component;


import com.project.flightbooking.domain.Fare;
import com.project.flightbooking.repository.FareRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
public class FareComponent {
    private static final Logger logger = LoggerFactory.getLogger(FareComponent.class);

    FareRepository faresRepository;

    public FareComponent() {

    }

    @Autowired
    public FareComponent(FareRepository faresRepository) {
        this.faresRepository = faresRepository;
    }

    public Fare getFare(String flightNumber, String flightDate) {
        logger.info("Looking for fares flightNumber " + flightNumber + " flightDate " + flightDate);
        return faresRepository.getFareByFlightNumberAndFlightDate(flightNumber, flightDate);
    }
}