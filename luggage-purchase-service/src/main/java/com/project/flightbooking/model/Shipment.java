package com.project.flightbooking.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

// Shipment
@Data
@ToString
public class Shipment implements Serializable {

    private String streetAddress;
    private String streetNumber;
    private String zip;
    private String shipmentDate;

}