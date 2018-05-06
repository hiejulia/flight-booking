package com.project.flightbooking.dao;




import com.project.flightbooking.domain.City;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class CityService implements CityDao{

    @PersistenceContext
    @Autowired
    private EntityManager em;

    public City getAllCities() {

        Query query = em.createQuery(
                "SELECT distinct(r.from) FROM Route r"
        );

        City city = new City();

        city.getCities().addAll(
                (List<String>) query.getResultList()
                        .stream()
                        .map(o -> o.toString().trim())
                        .collect(Collectors.toList())
        );

        return city;
    }
}