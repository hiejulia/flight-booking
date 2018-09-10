package com.project.flightbooking.domain;


import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.HashSet;
import java.util.Set;


@NodeEntity
public class User {
    @GraphId
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    // Address facturation - new HashSet<>() - @RelateTo
    @RelatedTo(type="FACTURE", direction= Direction.OUTGOING)
    private Set<Adress> adressesFacturation = new HashSet<>();

    @RelatedTo(type="TRAVAIL", direction= Direction.OUTGOING)
    private Set<Adress> adressesTravail = new HashSet<>();

    @RelatedTo(type="HABITE", direction= Direction.OUTGOING)
    private Set<Adress> adressesDomicile = new HashSet<>();

    public User() {
    }

    public void addAdresseFacturation(Adress adress) {
        if (adressesFacturation == null) {
            adressesFacturation = new HashSet<>();
        }
        adressesFacturation.add(adress);
    }

    public void addAdresseTravail(Adress adress) {
        if (adressesTravail == null) {
            adressesTravail = new HashSet<>();
        }
        adressesTravail.add(adress);
    }

    public void addAdresseDomicile(Adress adress) {
        if (adressesDomicile == null) {
            adressesDomicile = new HashSet<>();
        }
        adressesDomicile.add(adress);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Adress> getAdressesFacturation() {
        return adressesFacturation;
    }

    public void setAdressesFacturation(Set<Adress> adressesFacturation) {
        this.adressesFacturation = adressesFacturation;
    }

    public Set<Adress> getAdressesTravail() {
        return adressesTravail;
    }

    public void setAdressesTravail(Set<Adress> adressesTravail) {
        this.adressesTravail = adressesTravail;
    }

    public Set<Adress> getAdressesDomicile() {
        return adressesDomicile;
    }

    public void setAdressesDomicile(Set<Adress> adressesDomicile) {
        this.adressesDomicile = adressesDomicile;
    }
}