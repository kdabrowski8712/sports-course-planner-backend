package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "USERS")
public class User extends GenericUser {

    public User(@NotNull String name, @NotNull String surname, String description, Address address) {
        super(name, surname, description, address);
    }

    @Column(name = "user_id", unique = true)
    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @OneToMany(
            targetEntity = Reservation.class,
            mappedBy = "owner",
            fetch = FetchType.LAZY
    )
    private List<Reservation> reservations = new ArrayList<>();

}
