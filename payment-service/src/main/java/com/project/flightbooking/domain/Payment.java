package com.project.flightbooking.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Entity
@Table(name = "PAYMENT_ORDER") //
public class Payment {
    @Id
    @GeneratedValue
    private Long paymentId;

    @Embedded
    private Sender sender;

    @Embedded
    private Recipient recipient;

    @Embedded
    private BookingRecord bookingRecord;

    private Double totalAmount;

    private Date timestamp = new Date();

    private String paymentStatus;

}