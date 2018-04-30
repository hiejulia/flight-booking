package com.project.flightbooking.domain;


public class SearchQuery {

    // Search query : origin , destination , date : search for suitable flight
    private String origin;

    private String destination;

    private String flightDate;

    public SearchQuery() {
        super();
    }

    @Override
    public String toString() {
        return "SearchQuery [origin=" + origin + ", destination=" + destination + ", flightDate=" + flightDate + "]";
    }

    public SearchQuery(String origin, String destination, String flightDate) {
        super();
        this.origin = origin;
        this.destination = destination;
        this.flightDate = flightDate;
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

}