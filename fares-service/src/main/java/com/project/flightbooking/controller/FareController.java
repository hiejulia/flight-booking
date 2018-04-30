package com.project.flightbooking.controller;


import com.project.flightbooking.component.FareComponent;
import com.project.flightbooking.domain.Fare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/fares")
public class FareController {

    FareComponent faresComponent;

    @Autowired
    FareController(FareComponent faresComponent) {
        this.faresComponent = faresComponent;
    }

    /**
     * GET FARE BY FLIGHT NUMBER AND FLIGHT DATE
     * @param flightNumber
     * @param flightDate
     * @return
     */
    @RequestMapping("/get")
    Fare getFare(@RequestParam(value = "flightNumber") String flightNumber,
                 @RequestParam(value = "flightDate") String flightDate) {
        return faresComponent.getFare(flightNumber, flightDate);
    }
}