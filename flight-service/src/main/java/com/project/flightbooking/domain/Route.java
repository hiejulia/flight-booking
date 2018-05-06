package com.project.flightbooking.domain;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@Table(name = "route")
@XmlRootElement(name = "root")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Route {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "origin")
    private String from;

    @Column(name = "destination")
    private String to;

    private String airline;

    public Route() {
    }

    public Route(String from, String to) {
        this();
        this.from = from;
        this.to = to;
    }

    public Route(String airline) {
        this.airline = airline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Route)) {
            return false;
        }
        Route that = (Route) o;
        return this.from.equalsIgnoreCase(that.from) && this.to.equalsIgnoreCase(that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.to.toLowerCase(), this.from.toLowerCase());
    }

    @Override
    public String toString() {
        return this.from + "-" + this.to;
    }
}