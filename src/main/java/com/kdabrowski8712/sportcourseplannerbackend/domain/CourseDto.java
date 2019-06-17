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
public class CourseDto extends  GenericActivity {

    public CourseDto(@NotNull String name, String description, @NotNull float price, @NotNull String category,
                     Address address, LocalDateTime startDate, LocalDateTime endDate, int minNrOfUsers, int maxNrOfUsers) {
        super(name, description, price, category, address);
        this.startDate = startDate;
        this.endDate = endDate;
        this.minNrOfUsers = minNrOfUsers;
        this.maxNrOfUsers = maxNrOfUsers;

        reservationsIds = new ArrayList<>();
        instructorsIds = new ArrayList<>();
    }

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int minNrOfUsers;
    private int maxNrOfUsers;
    private List<Long> reservationsIds;
    private List<Long> instructorsIds;


}
