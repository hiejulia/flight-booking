package com.project.flightbooking.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Booking {


    // Booking : as embeddable: bookingId, bookingTimeStamp
    private String bookingId;

    private Date bookingTimestamp = new Date();
}
