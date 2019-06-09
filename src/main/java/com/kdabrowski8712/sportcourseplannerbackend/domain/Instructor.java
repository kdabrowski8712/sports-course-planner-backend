package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "INSTRUCTORS")
public class Instructor extends GenericUser {
    @Id
    @Column(name = "instructor_id" , unique = true)
    @GeneratedValue
    @NotNull
    private Long id;
}
