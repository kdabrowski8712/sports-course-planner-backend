package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class GenericUserDto {

    protected String name;
    protected String surname;
    protected String description;
    protected Address address;

    public GenericUserDto(String name, String surname, String description, Address address) {
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.address = address;
    }
}
