package com.project.flightbooking.domain;


import java.math.BigInteger;

public class Flight {

    private int capacity;

    public Flight(String name, BigInteger id, int capacity) {
        this.capacity = capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}