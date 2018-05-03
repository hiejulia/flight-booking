package com.project.flightbooking.repository;


import com.project.flightbooking.entity.BookingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingRecordRepository extends JpaRepository<BookingRecord, Long> {

    // Find one booking record by id


}