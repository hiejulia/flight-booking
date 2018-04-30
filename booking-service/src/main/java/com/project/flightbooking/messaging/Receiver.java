package com.project.flightbooking.messaging;


import com.project.flightbooking.service.BookingRecordService;
import com.project.flightbooking.service.BookingStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class Receiver {

    BookingRecordService bookingRecordService;

    @Autowired
    public Receiver(BookingRecordService bookingRecordService){
        this.bookingRecordService = bookingRecordService;
    }

    // Listen for Queue : CheckInQ -> Change the BookingRecordStatus
    @RabbitListener(queues = "CheckINQ")
    public void processMessage(Long bookingID ) {
//        System.out.println(bookingID);
        bookingRecordService.updateStatus(BookingStatus.CHECKED_IN, bookingID);
    }

}