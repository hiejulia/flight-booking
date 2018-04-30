package com.project.flightbooking.controller;


import com.project.flightbooking.entity.BookingRecord;
import com.project.flightbooking.service.BookingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



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