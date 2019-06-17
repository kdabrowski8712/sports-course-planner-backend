package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Getter
public class InstructorDto extends GenericUser {

    private List<Long> scheduleIds;
    private List<Long> coursesIds;

    public InstructorDto(@NotNull String name, @NotNull String surname, String description, Address address) {
        super(name, surname, description, address);
        scheduleIds = new ArrayList<>();
        coursesIds = new ArrayList<>();
    }
}
