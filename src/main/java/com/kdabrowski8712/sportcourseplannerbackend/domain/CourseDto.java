package com.kdabrowski8712.sportcourseplannerbackend.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class CourseDto extends  GenericActivityDto {

    public CourseDto(Long id, String name, String description, float price, String category,
                     Address address, LocalDateTime startDate, LocalDateTime endDate,
                     int minNrOfUsers, int maxNrOfUsers, LocalDateTime resPeriodStart,
                     LocalDateTime resPeriodEnd) {
        super(name, description, price, category, address);
        this.startDate = startDate;
        this.endDate = endDate;
        this.minNrOfUsers = minNrOfUsers;
        this.maxNrOfUsers = maxNrOfUsers;
        this.id = id;
        this.reservationPeriodStart = resPeriodStart;
        this.reservationPeriodEnd = resPeriodEnd;
    }

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime reservationPeriodStart;
    private LocalDateTime reservationPeriodEnd;
    private int minNrOfUsers;
    private int maxNrOfUsers;
    private List<Long> reservationsIds = new ArrayList<>();
    private List<Long> instructorsIds = new ArrayList<>();


}
