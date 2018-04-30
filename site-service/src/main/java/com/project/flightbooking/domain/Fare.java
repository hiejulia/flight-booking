package com.project.flightbooking.domain;


public class Fare {

    private Long id;

    private String fare;

    private String currency;

    public Fare(String fare, String currency) {
        super();
        this.fare = fare;
        this.currency = currency;
    }

    public Fare() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Fares [id=" + id + ", fare=" + fare + ", currency=" + currency + "]";
    }

}