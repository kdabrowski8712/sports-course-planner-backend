package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "INDIVIDUAL_TRAINING_OFFERS")
public class IndividualTrainingOffer extends GenericActivity {

    public IndividualTrainingOffer(@NotNull String name, String description, @NotNull float price,
                                   @NotNull String category, Address address,
                                   LocalDateTime startHour, int duration) {

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
            mappedBy = "individualTrainingOffer",
            fetch = FetchType.LAZY
    )
    private List<Reservation> reservations;

    @OneToOne(fetch = FetchType.LAZY)
    private ScheduleEntry scheduleEntry;

}
