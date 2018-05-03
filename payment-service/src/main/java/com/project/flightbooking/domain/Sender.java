package com.project.flightbooking.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sender {
    // Sender : userName, phoneNumver
    private String userName;

    private String phoneNumber;

    private String creditCardNumber;

    private String expirationDate;

    private String securityCode;

}