package com.project.flightbooking.repository;

import com.project.flightbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking,String>{
    Collection<Booking> findByName(String name);

    List<Booking> findAll();



}
