package com.project.flightbooking.controller;


import com.project.flightbooking.entity.CheckInRecord;
import com.project.flightbooking.service.CheckinService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/checkin")
public class CheckInController {

    CheckinService checkinService;

    @Autowired
    CheckInController(CheckinService checkinService) {
        this.checkinService = checkinService;
    }

    /**
     * GET ONE CHECK IN BY ID
     * @param id
     * @return
     */
    @RequestMapping("/get/{id}")
    CheckInRecord getCheckIn(@PathVariable Long id) {
        return checkinService.getCheckInRecord(id);
    }

    /**
     * POST /checkin
     * @param checkIn
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    Long checkIn(@RequestBody CheckInRecord checkIn) {
        return checkinService.checkIn(checkIn);
    }

}