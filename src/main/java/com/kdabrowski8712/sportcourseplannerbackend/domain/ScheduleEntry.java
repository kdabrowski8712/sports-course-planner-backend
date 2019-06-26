package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
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

    public ScheduleEntry(LocalDateTime startHour, int duration, boolean reserved) {
        this.startHour = startHour;
        this.duration = duration;
        this.reserved = reserved;
    }

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToOne(fetch = FetchType.LAZY)
    private Reservation reservation;

}
