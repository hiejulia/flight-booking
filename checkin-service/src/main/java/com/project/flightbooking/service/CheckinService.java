package com.project.flightbooking.service;


import java.util.Date;

import com.project.flightbooking.entity.CheckInRecord;
import com.project.flightbooking.messaging.Sender;
import com.project.flightbooking.repository.CheckinRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@Component
public class CheckinService {
    private static final Logger logger = LoggerFactory.getLogger(CheckinService.class);

    CheckinRepository checkinRepository;

    Sender sender;

    @Autowired
    CheckinService(CheckinRepository checkinRepository, Sender sender) {
        this.checkinRepository = checkinRepository;
        this.sender = sender;
    }

    /**
     * Check In main function
     * @param checkIn
     * @return
     */
    public Long checkIn(CheckInRecord checkIn) {
        checkIn.setCheckInTime(new Date());// set time for checkin
        logger.info("Saving checkin " + checkIn);
        // save
        Long id = checkinRepository.save(checkIn).getId();
        logger.info("Successfully saved checkin " + checkIn);

        // Send a message back to BOOKING - SERVICE to update the status
        logger.info("Sending booking id " + id);
        sender.send(id);
        return id;
    }

    public CheckInRecord getCheckInRecord(Long id) {
        return checkinRepository.getOne(id);
    }

}