package com.project.flightbooking.repository;


import com.project.flightbooking.entity.CheckInRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CheckinRepository extends JpaRepository<CheckInRecord, Long> {

}