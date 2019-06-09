package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "COURSES")
public class Course extends GenericActivity {

    public Course(@NotNull String name, String description, @NotNull float price, @NotNull String category, Address address,
                  @NotNull LocalDateTime startDate, @NotNull LocalDateTime endDate, int minNrOfUsers, @NotNull int maxNrOfUsers) {
        super(name, description, price, category, address);
        this.startDate = startDate;
        this.endDate = endDate;
        this.minNrOfUsers = minNrOfUsers;
        this.maxNrOfUsers = maxNrOfUsers;
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "course_id", unique = true)
    private Long id;

    @NotNull
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @NotNull
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "min_users")
    private int minNrOfUsers;

    @NotNull
    @Column(name = "max_users")
    private int maxNrOfUsers;
}
