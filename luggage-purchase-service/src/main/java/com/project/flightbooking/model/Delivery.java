package com.project.flightbooking.model;

import jdk.jfr.DataAmount;

import java.io.Serializable;

@Data

public class Delivery implements Serializable{

    private String personDeliveryName;

    private Date deliveryDate;


    @Override
    public String toString() {

    }
}
