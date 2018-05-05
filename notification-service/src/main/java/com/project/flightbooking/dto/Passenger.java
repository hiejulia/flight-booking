package com.project.flightbooking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Passenger {

    private Long id;

    @JsonProperty("username")
    private String username;

    private String email;

    private String userId;

    private String passengerCode;

}
