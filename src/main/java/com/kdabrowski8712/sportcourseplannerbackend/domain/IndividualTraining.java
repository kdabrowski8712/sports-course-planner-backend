package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "INDIVIDUAL_TRAININGS")
public class IndividualTraining extends GenericActivity {

    public IndividualTraining(@NotNull String name, String description, @NotNull float price,
                              @NotNull String category, Address address,
                              LocalDateTime startHour, int duration) {

        super(name, description, price, category, address);
        this.startHour = startHour;
        this.duration = duration;
    }

    @Id
    @Column(name = "training_id" , unique = true)
    @GeneratedValue
    @NotNull
    private Long id;

    @Column(name = "start_hour")
    private LocalDateTime startHour;
    @Column(name="duration")
    private int duration;

}
