package com.project.flightbooking.entity;


import javax.persistence.*;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Table
@Data
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String gender;

    private String address;

    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_ID")
    @JsonIgnore
    private BookingRecord bookingRecord;


}