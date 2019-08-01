package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "INSTRUCTORS")
public class Instructor extends GenericUser {
    @Id
    @Column(name = "instructor_id", unique = true)
    @GeneratedValue
    @NotNull
    private Long id;

    @OneToMany(
            targetEntity = ScheduleEntry.class,
            mappedBy = "instructor",
            fetch = FetchType.LAZY
    )
    private List<ScheduleEntry> schedule = new ArrayList<>();

    @ManyToMany(mappedBy = "instructors")
    private List<Course> courses = new ArrayList<>();

    @OneToMany (
            targetEntity = PrivateOffer.class,
            mappedBy = "instructor",
            fetch = FetchType.LAZY
    )
    private List<PrivateOffer> trainingoffers = new ArrayList<>();


    public Instructor(@NotNull String name, @NotNull String surname, String description, Address address) {
        super(name, surname, description, address);
    }

    public void addCourse(Course courseToAdd) {
        courses.add(courseToAdd);
    }

}
