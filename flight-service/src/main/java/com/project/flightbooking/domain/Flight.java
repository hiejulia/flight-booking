package com.project.flightbooking.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;



public class Flight extends BaseEntity<BigInteger> {
    // Flight : capacity

    private int capacity;

    public Flight(@JsonProperty("name") String name, @JsonProperty("id") BigInteger id, @JsonProperty("capacity") int capacity) {
        super(id, name);
        this.capacity = capacity;
    }


    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }


    @Override
    public String toString() {
        return String.format("{id: %s, name: %s, capacity: %s}",
                this.getId(), this.getName(), this.getCapacity());
    }

}