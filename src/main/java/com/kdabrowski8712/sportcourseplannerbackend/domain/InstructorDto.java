package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class InstructorDto extends GenericUser {

    private List<Long> scheduleIds = new ArrayList<>();
    private List<Long> coursesIds = new ArrayList<>();
    private List<Long> offerIds = new ArrayList<>();
    private Long id;

    public InstructorDto(@NotNull String name, @NotNull String surname, String description, Address address) {
        super(name, surname, description, address);
        scheduleIds = new ArrayList<>();
        coursesIds = new ArrayList<>();
        offerIds = new ArrayList<>();
    }
}
