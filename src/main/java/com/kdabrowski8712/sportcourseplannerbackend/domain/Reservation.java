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
@Table(name = "RESERVATIONS")
public class Reservation {
    @GeneratedValue
    @Id
    @NotNull
    @Column(name = "reservation_id", unique = true)
    private Long id;
    private boolean valid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

}
