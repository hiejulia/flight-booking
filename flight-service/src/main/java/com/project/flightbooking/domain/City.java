package com.project.flightbooking.domain;




import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;
import java.util.TreeSet;

@XmlRootElement
public class City extends FlightResponse {

    @XmlElementWrapper
    private Set<String> cities; // set of cities


    public City() {
        this.cities = new TreeSet<>();
    }

    public Set<String> getCities() {
        return cities;
    }

    public void setCities(Set<String> cities) {
        this.cities = cities;
    }
}