package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleEntryDto {
    private Long id;
    private LocalDateTime startHour;
    private int duration;
    private boolean reserved;
    private Long instructorId;
    private Long reservationId;
}
