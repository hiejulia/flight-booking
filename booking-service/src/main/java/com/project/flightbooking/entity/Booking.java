package com.project.flightbooking.entity;

import java.time.LocalDate;
import java.time.LocalTime;


public class Booking extends BaseEntity<String> {

    private String airportId;

    private String userId;

    private LocalDate date;

    private LocalTime time;

    private String flightId;

    public String getAirportId() {
        return airportId;
    }

    public void setAirportId(String airportId) {
        this.airportId = airportId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public Booking(String id, String name, String airportId, String userId, LocalDate date, LocalTime time, String flightId) {
        super(id, name);
        this.airportId = airportId;
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.flightId = flightId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "airportId='" + airportId + '\'' +
                ", userId='" + userId + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", flightId='" + flightId + '\'' +
                '}';
    }
}