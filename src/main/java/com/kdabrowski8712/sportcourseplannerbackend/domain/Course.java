package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "COURSES")
public class Course extends GenericActivity {

    public Course(@NotNull String name, String description, @NotNull float price,
                  @NotNull String category, Address address,
                  @NotNull LocalDateTime startDate,
                  @NotNull LocalDateTime endDate, int minNrOfUsers,
                  @NotNull int maxNrOfUsers, @NotNull LocalDateTime resPeriodStart,
                  @NotNull LocalDateTime resPeriodEnd) {

        super(name, description, price, category, address);

        this.startDate = startDate;
        this.endDate = endDate;
        this.minNrOfUsers = minNrOfUsers;
        this.maxNrOfUsers = maxNrOfUsers;
        this.reservation_period_start = resPeriodStart;
        this.reservation_period_end = resPeriodEnd;
        this.status = "" + CourseStatus.NEW;
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

    @NotNull
    @Column(name = "res_period_start")
    private LocalDateTime reservation_period_start;

    @NotNull
    @Column(name = "res_period_end")
    private LocalDateTime reservation_period_end;


    @Column(name = "min_users")
    private int minNrOfUsers;

    @NotNull
    @Column(name = "max_users")
    private int maxNrOfUsers;

    @NotNull
    @Column(name = "course_status")
    private String status;


    @OneToMany(
            targetEntity = Reservation.class,
            mappedBy = "course",
            fetch = FetchType.LAZY
    )
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "JOIN_COURSE_INSTRUCTOR",
            joinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "instructor_id", referencedColumnName = "instructor_id")}
    )
    private List<Instructor> instructors = new ArrayList<>();


}
