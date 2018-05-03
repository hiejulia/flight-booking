package com.project.flightbooking.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable // this class can be embedded into another class: e.g: Payment class
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Recipient {

    // Recipent : airport name, address

    private String airportName;

    private String airportAddress;

}