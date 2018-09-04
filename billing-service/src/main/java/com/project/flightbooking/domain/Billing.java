package com.project.flightbooking.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Billing implements Serializable {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String flightNo;

    private String arrival;

    private String departure;

    private String price;

    private String agency;



}