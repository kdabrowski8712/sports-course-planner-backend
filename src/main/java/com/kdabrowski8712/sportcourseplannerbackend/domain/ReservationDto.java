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
public class ReservationDto {
    private Long id;
    private boolean valid;
    private LocalDateTime validUntil;
    private Long ownerId;
    private Long courseId;
    private Long privateOfferId;
    private Long scheduleEntryId;
}
