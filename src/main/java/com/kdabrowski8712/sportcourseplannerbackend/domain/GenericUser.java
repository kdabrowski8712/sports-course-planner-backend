package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class GenericUser {

    @NotNull
    @Column(name = "name")
    protected String name;
    @NotNull
    @Column(name = "surname")
    protected String surname;
    @Column(name = "description")
    protected String description;

    @Embedded
    protected Address address;

    public GenericUser(@NotNull String name, @NotNull String surname, String description, Address address) {
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.address = address;
    }
}
