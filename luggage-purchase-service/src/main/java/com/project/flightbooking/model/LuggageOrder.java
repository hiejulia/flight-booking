package com.project.flightbooking.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Document
@ToString
public class LuggageOrder implements Serializable {

    @Id
    public String orderNumber;
    public Date orderDate;
    public String userName;

    @Version
    private Long version;

    private LuggageOrderStatusEnum status;

    private Passenger passenger;
    private List<Luggage> luggageList;
    private Shipment shipment;
    private Delivery delivery;
    private BigDecimal total;

}