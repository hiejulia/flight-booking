package com.project.flightbooking.domain;

public class Flight {

    private Long id;

    private String flightNumber;

    private String origin;

    private String destination;

    private String flightDate;

    Fare fares;

    public Flight() {
        super();
    }

    public Flight(String flightNumber, String origin, String destination, String flightDate, Fare fares) {
        super();
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.flightDate = flightDate;
        this.fares = fares;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public Fare getFares() {
        return fares;
    }

    public void setFares(Fare fares) {
        this.fares = fares;
    }

    @Override
    public String toString() {
        return "Flight [id=" + id + ", flightNUmber=" + flightNumber + ", origin=" + origin + ", destination="
                + destination + ", flightDate=" + flightDate + ", fares=" + fares + "]";
    }

}