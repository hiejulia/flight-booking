package com.project.flightbooking.dao;



import com.project.flightbooking.domain.Flight;
import com.project.flightbooking.domain.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by michael on 7/30/15.
 */
public class RouteService implements RouteDao {

    @PersistenceContext
    @Autowired
    private EntityManager em;

    // Find flight in the route From - To
    public Flight findAirlines(String from, String to) {
        Query query = em.createQuery(
                // Query : select distince r.air
                "SELECT distinct(r.airline) FROM Route r WHERE " +
                        "(r.from = :from AND r.to = :to) or " +
                        "(r.from = :to and r.to = :from)");

        query.setParameter("from", from.trim()); // from

        query.setParameter("to", to.trim()); // to


        List<String> airlines = (List<String>)query.getResultList()
                .stream()
                .map(o -> o.toString().trim())
                .collect(Collectors.toList());

        return new Flight(new TreeSet<>(airlines));
    }

    public Flight findAirlines(Route route) {
        return findAirlines(route.getFrom(), route.getTo());
    }
}