package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SCHEDULE_ENTRIES")
public class ScheduleEntry {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "entry_id")
    private Long id;
    @Column(name="start_hour")
    private LocalDateTime startHour;
    @Column(name = "duration")
    private int duration;
    @Column(name="reserved")
    private boolean reserved;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToOne(fetch = FetchType.LAZY)
    private Reservation reservation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "individual_training_id")
    private IndividualTrainingOffer individualTrainingOffer;


}
