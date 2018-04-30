package com.project.flightbooking.repository;


import com.project.flightbooking.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    // Find by Flight number and flight date
    Inventory findByFlightNumberAndFlightDate(String flightNumber, String flightDate);

}