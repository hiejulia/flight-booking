package com.project.flightbooking.repository;


import com.project.flightbooking.domain.Fare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FareRepository extends JpaRepository<Fare, Long> {

    // Get fare by flight number and flight date
    Fare getFareByFlightNumberAndFlightDate(String flightNumber, String flightDate);
}