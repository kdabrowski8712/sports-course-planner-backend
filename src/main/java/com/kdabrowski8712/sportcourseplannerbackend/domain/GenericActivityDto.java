package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class GenericActivityDto {

    protected String name;
    protected String description;
    protected float price;
    protected String category;
    protected Address address;

    public GenericActivityDto(String name, String description, float price, String category, Address address) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.address = address;
    }
}
