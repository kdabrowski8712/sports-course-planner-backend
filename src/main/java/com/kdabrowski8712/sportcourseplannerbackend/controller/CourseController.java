package com.kdabrowski8712.sportcourseplannerbackend.controller;

import com.kdabrowski8712.sportcourseplannerbackend.domain.Course;
import com.kdabrowski8712.sportcourseplannerbackend.domain.CourseDto;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/apiv1")
public class CourseController {

    @GetMapping(value = "/courses")
    public List<CourseDto> getCourses() {
        return new ArrayList<>();
    }

    @GetMapping(value = "/courses/{courseId}")
    public CourseDto getCourse(@PathVariable Long courseId){
        return new CourseDto();
    }

    @GetMapping("/courses/instructor/{instructorId}")
    public List<CourseDto> getCoursesByInstructor (@PathVariable Long instructorId){
        return new ArrayList<>();
    }

    @GetMapping("/courses/reservation/{reservationId}")
    public List<CourseDto> getCoursesByReservation (@PathVariable Long reservationId){
        return new ArrayList<>();
    }

    @PostMapping(value = "/courses", consumes = APPLICATION_JSON_VALUE)
    public CourseDto addCourse(@RequestBody CourseDto courseDto) {
        return new CourseDto();
    }

    @DeleteMapping(name = "/courses/{courseId}")
    public void removeCourse(@PathVariable Long courseId) {

    }

    @PutMapping(value = "/courses")
    public CourseDto updateCourse(@RequestBody CourseDto courseDto) {
        return new CourseDto();
    }



}
