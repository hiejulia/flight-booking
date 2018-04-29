package com.project.flightbooking.valueobject;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingVO
{

    private String name;

    private String id;

    private String airportId;

    private String userId;

    private LocalDate date;

    private LocalTime time;

    private String flightId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public BookingVO(String name, String id, String airportId, String userId, LocalDate date, LocalTime time, String flightId) {
        this.name = name;
        this.id = id;
        this.airportId = airportId;
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.flightId = flightId;
    }

    @Override
    public String
    toString() {
        return "BookingVO{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", airportId='" + airportId + '\'' +
                ", userId='" + userId + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", flightId='" + flightId + '\'' +
                '}';
    }
}
