package com.project.flightbooking.service;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.project.flightbooking.entity.BookingRecord;
import com.project.flightbooking.entity.Inventory;
import com.project.flightbooking.entity.Passenger;
import com.project.flightbooking.exception.BookingException;
import com.project.flightbooking.messaging.Sender;
import com.project.flightbooking.repository.BookingRecordRepository;
import com.project.flightbooking.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


@Service
@Transactional
public class BookingRecordService {
    private static final Logger logger = LoggerFactory.getLogger(BookingRecordService.class);

    BookingRecordRepository bookingRecordRepository;
    InventoryRepository inventoryRepository;

    // @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    private Sender sender;

    @Autowired
    public BookingRecordService(BookingRecordRepository bookingRecordRepository, Sender sender,
                            InventoryRepository inventoryRepository) {
        this.bookingRecordRepository = bookingRecordRepository;
        this.restTemplate = new RestTemplate();
        this.sender = sender;
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * BOOK A FLIGHT
     * @param record
     * @return
     */
    public Long book(BookingRecord record) {
        // call fares to get fare
        String fareUrl = env.getProperty("custom.fare.service.url");

        Fare fare = restTemplate.getForObject(
                fareUrl + "/get?flightNumber=" + record.getFlightNumber() + "&flightDate=" + record.getFlightDate(),
                Fare.class);
        logger.info("Fare received " + fare);
        // check fare
        if (!record.getFare().equals(fare.getFare())) {
            throw new BookingException("Fare is not found");
        }

        logger.info("calling inventory to get inventory");
        // check inventory
        Inventory inventory = inventoryRepository.findByFlightNumberAndFlightDate(record.getFlightNumber(),
                record.getFlightDate());
        if (!inventory.isAvailable(record.getPassengers().size())) {
            throw new BookingException("No more seats avaialble");
        }
        logger.info("successfully checked inventory" + inventory);
        logger.info("calling inventory to update inventory");
        // update inventory
        inventory.setAvailable(inventory.getAvailable() - record.getPassengers().size());
        inventoryRepository.saveAndFlush(inventory);
        logger.info("sucessfully updated inventory");

        // Save booking
        record.setStatus(BookingStatus.BOOKING_CONFIRMED);
        Set<Passenger> passengers = record.getPassengers();
        passengers.forEach(passenger -> passenger.setBookingRecord(record));
        record.setBookingDate(new Date());
        Long id = bookingRecordRepository.save(record).getId();
        logger.info("Successfully saved booking");

        // send a message to search microservice to update inventory
        // Send a message to RabbitMQ queue to search microservices to update inventory
        logger.info("sending a booking event");
        Map<String, Object> bookingDetails = new HashMap<String, Object>();
        bookingDetails.put("FLIGHT_NUMBER", record.getFlightNumber());
        bookingDetails.put("FLIGHT_DATE", record.getFlightDate());
        bookingDetails.put("NEW_INVENTORY", inventory.getBookableInventory());
        sender.send(bookingDetails);
        logger.info("booking event successfully delivered " + bookingDetails);
        return id;
    }

    /**
     * GET ONE BOOKING RECORD BY ID
      * @param id
     * @return
     */
    public BookingRecord getBooking(Long id) {
        return bookingRecordRepository.getOne(id);
    }

    /**
     * UPDATE BOOKING RECORD STATUS
     * @param status
     * @param bookingId
     */
    public void updateStatus(String status, Long bookingId) {
        BookingRecord record = bookingRecordRepository.getOne(bookingId);
        record.setStatus(status);
    }

}