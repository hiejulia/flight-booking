package com.project.flightbooking.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LuggageInPreiceListNotFoundException {
    public static final String DEFAULT_MESSAGE = "Not found";

    public GoodsInPriceListNotFoundException(String msg) {
        super( msg);
    }

    public GoodsInPriceListNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }
}
