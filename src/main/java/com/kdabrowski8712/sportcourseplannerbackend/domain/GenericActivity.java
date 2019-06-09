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
    private String name;

    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "price")
    private float price;
    @NotNull
    @Column(name = "category")
    private String category;
    @Embedded
    private Address address;

}
