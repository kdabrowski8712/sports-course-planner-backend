package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class GenericActivity {

    @NotNull
    @Column(name = "name")
    protected String name;

    @Column(name = "description")
    protected String description;
    @NotNull
    @Column(name = "price")
    protected float price;
    @NotNull
    @Column(name = "category")
    protected String category;
    @Embedded
    protected Address address;

}
