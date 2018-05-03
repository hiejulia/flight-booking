package com.project.flightbooking.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.flightbooking.entity.Airport;
import com.project.flightbooking.entity.Flight;
import com.project.flightbooking.entity.Passenger;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BookingDeliveryRequest {

    private Long id;

    private Passenger passenger;

    private Airport airport;

    private List<Flight> flights = new ArrayList<>(0);

}