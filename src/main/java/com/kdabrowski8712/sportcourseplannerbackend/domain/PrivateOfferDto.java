package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Setter
public class PrivateOfferDto extends GenericActivity {

    private int duration;
    private List<Long> reservationIds = new ArrayList<>();
    private Long instructor_id;
    private Long id;

    public PrivateOfferDto(Long id, @NotNull String name, String description, @NotNull float price,
                           @NotNull String category, Address address, int duration) {

        super(name, description, price, category, address);
        this.duration = duration;
        this.id = id;
    }

}
