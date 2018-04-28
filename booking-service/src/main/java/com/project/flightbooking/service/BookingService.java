package com.project.flightbooking.service;




import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.project.flightbooking.entity.Booking;
import com.project.flightbooking.entity.Entity;
import com.project.flightbooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BookingService {


    @Autowired
    private BookingRepository bookingRepository;



    public void add(Booking booking) throws Exception {
//        if (bookingRepository.getOne(i) {
//            throw new Exception(String.format("There is already a product with the name - %s", booking.getName()));
//        }

        if (booking.getName() == null || "".equals(booking.getName())) {
            throw new Exception("Booking name cannot be null or empty string.");
        }
        bookingRepository.save(booking);
    }

    public Collection<Booking> findByName(String name) throws Exception {
        return bookingRepository.findByName(name);
    }


    public void update(Booking booking) throws Exception {
        Booking booking1 = bookingRepository.getOne(booking.getId());
        booking1.setAirportId(booking.getAirportId());
        booking1.setDate(booking.getDate());
        booking1.setFlightId(booking.getFlightId());
        booking1.setUserId(booking.getUserId());
        booking1.setName(booking.getName());
    }


    public void delete(String id) throws Exception {
        bookingRepository.delete(bookingRepository.getOne(id));
    }


    public Entity findById(String id) throws Exception {
        return bookingRepository.getOne(id);
    }


    public Collection<Booking> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}