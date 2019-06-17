package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class IndividualTrainingDto extends GenericActivity {
    private LocalDateTime startHour;
    private int duration;
    private List<Long> reservationIds;

    public IndividualTrainingDto(@NotNull String name, String description, @NotNull float price,
                                 @NotNull String category, Address address,
                                 LocalDateTime startHour, int duration) {

        super(name, description, price, category, address);
        this.startHour = startHour;
        this.duration = duration;

        reservationIds = new ArrayList<>();
    }
}
