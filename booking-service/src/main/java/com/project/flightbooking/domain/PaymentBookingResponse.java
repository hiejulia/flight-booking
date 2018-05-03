package com.project.flightbooking.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PaymentBookingResponse {

    private Long paymentId;

    private String orderId;
}