package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
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


}
