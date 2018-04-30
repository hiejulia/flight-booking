package com.project.flightbooking.domain;


import javax.persistence.*;

@Entity
@Table
public class Fare {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String flightNumber;

    private String flightDate;

    private String fare;

    public Fare() {
        super();
    }

    public Fare(String flightNumber, String flightDate, String fare) {
        super();
        this.flightNumber = flightNumber;
        this.flightDate = flightDate;
        this.fare = fare;
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

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    @Override
    public String toString() {
        return "Fares [id=" + id + ", flightNumber=" + flightNumber + ", flightDate=" + flightDate + ", fare=" + fare
                + "]";
    }

}