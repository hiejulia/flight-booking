package com.project.flightbooking.domain;

import java.util.ArrayList;
import java.util.List;

public class Organisation {

    private Long id;

    private String name;

    private String address;

    private List<Passenger> passengers = new ArrayList<>();

}
