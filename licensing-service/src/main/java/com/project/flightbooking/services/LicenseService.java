package com.project.flightbooking.services;


import com.project.flightbooking.clients.AirportDiscoveryClient;
import com.project.flightbooking.clients.AirportFeignClient;
import com.project.flightbooking.clients.AirportRestTemplateClient;
import com.project.flightbooking.config.ServiceConfig;
import com.project.flightbooking.model.Airport;
import com.project.flightbooking.model.License;
import com.project.flightbooking.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    ServiceConfig config;

    @Autowired
    AirportFeignClient organizationFeignClient;

    @Autowired
    AirportRestTemplateClient organizationRestClient;

    @Autowired
    AirportDiscoveryClient organizationDiscoveryClient;

    // Retrieve airport information
    private Airport retrieveAirportInfo(String airportId, String clientType) {
        Airport organization = null;

        switch (clientType) {
            case "feign":

                organization = organizationFeignClient.getOrganization(organizationId);
                break;
            case "rest":

                organization = organizationRestClient.getOrganization(organizationId);
                break;
            case "discovery":
                // get airport by id
                organization = organizationDiscoveryClient.getOrganization(organizationId);
                break;
            default:
                organization = organizationRestClient.getOrganization(organizationId);
                break;
        }

        return organization;
    }

    // Get license from airport
    public Airport getLicense(String organizationId, String licenseId, String clientType) {
        // FInd
        Airport license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        Airport org = retrieveOrgInfo(organizationId, clientType);

        return license
                .withOrganizationName( org.getName())
                .withContactName( org.getContactName())
                .withContactEmail( org.getContactEmail())
                .withContactPhone( org.getContactPhone())
                .withComment(config.getExampleProperty());
    }

    public List<Airport> getLicensesByOrg(String organizationId) {
        return licenseRepository.findByOrganizationId(organizationId);
    }

    public void saveLicense(License license) {
        license.withId(UUID.randomUUID().toString());
        licenseRepository.save(license);

    }

    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    public void deleteLicense(License license) {
        licenseRepository.delete(license);
    }

}