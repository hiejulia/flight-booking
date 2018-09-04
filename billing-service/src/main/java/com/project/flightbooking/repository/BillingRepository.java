package com.project.flightbooking.repository;



import com.project.flightbooking.domain.Billing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BillingRepository extends MongoRepository<Billing, String> {

    List<Billing> findByFlightNo(@Param("flightNo") String flightNo);

    Billing findById(@Param("id") String id);
}