package com.project.flightbooking.controller;


import com.netflix.discovery.converters.Auto;
import com.project.flightbooking.entity.Booking;
import com.project.flightbooking.entity.BookingRecord;
import com.project.flightbooking.entity.Flight;
import com.project.flightbooking.service.BookingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingRecordController {
    BookingRecordService bookingRecordService;


    @Autowired
    BookingRecordController(BookingRecordService bookingRecordService) {
        this.bookingRecordService = bookingRecordService;
    }

    /**
     * POST NEW BOOKING
     * @param record
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    long book(@RequestBody BookingRecord record) {
        return bookingRecordService.book(record);
    }

    /**
     * GET ONE BOOKING BY ID
     * @param id
     * @return
     */
    @RequestMapping("/get/{id}")
    BookingRecord getBooking(@PathVariable long id) {
        return bookingRecordService.getBooking(id);
    }




}