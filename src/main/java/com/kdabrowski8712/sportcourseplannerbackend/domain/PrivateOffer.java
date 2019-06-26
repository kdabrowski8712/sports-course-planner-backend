package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRIVATE_TRAINING_OFFERS")
public class PrivateOffer extends GenericActivity {

    public PrivateOffer(@NotNull String name, String description, @NotNull float price,
                        @NotNull String category, Address address, int duration) {

        super(name, description, price, category, address);
        this.duration = duration;
    }

    @Id
    @Column(name = "training_id" , unique = true)
    @GeneratedValue
    @NotNull
    private Long id;

    @Column(name = "duration")
    private int duration;


    @OneToMany (
            targetEntity = Reservation.class,
            mappedBy = "privateOffer",
            fetch = FetchType.LAZY
    )
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
}
