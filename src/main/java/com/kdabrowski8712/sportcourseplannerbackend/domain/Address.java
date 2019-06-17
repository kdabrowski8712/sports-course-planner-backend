package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Address {

    private String country;
    private String town;
    private String street;
    private int buildingNr;
    private int houseNr;
    private String propertyName;

    public Address (Address secondOne) {
        this.country = secondOne.getCountry();
        this.buildingNr = secondOne.getBuildingNr();
        this.street = secondOne.getStreet();
        this.town = secondOne.getTown();
        this.houseNr = secondOne.getHouseNr();
        this.propertyName = secondOne.getPropertyName();
    }
}
