package com.project.flightbooking.repository;



import com.project.flightbooking.model.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseRepository extends CrudRepository<License, Long> {

    // Find by airportID
    public List<License> findByAirportId(Long airportId);

    // Find by airportID and license ID
    public License findByAirportIdAndLicenseId(String airportId, String licenseId);

}