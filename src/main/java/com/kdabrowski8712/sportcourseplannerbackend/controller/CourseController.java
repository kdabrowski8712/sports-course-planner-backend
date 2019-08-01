package com.kdabrowski8712.sportcourseplannerbackend.controller;

import com.kdabrowski8712.sportcourseplannerbackend.domain.Address;
import com.kdabrowski8712.sportcourseplannerbackend.domain.Course;
import com.kdabrowski8712.sportcourseplannerbackend.domain.CourseDto;
import com.kdabrowski8712.sportcourseplannerbackend.mapper.CourseMapper;
import com.kdabrowski8712.sportcourseplannerbackend.service.CourseDBService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/apiv1")
public class CourseController {

    @Autowired
    private CourseDBService courseDBService;

    @Autowired
    private CourseMapper courseMapper;

    @GetMapping(value = "/courses")
    public List<CourseDto> getCourses() {

        return courseMapper.mapToCourseDtoList(courseDBService.getAllCourses());
    }

    @GetMapping(value = "/courses/{courseId}")
    public CourseDto getCourse(@PathVariable Long courseId){

        LocalDateTime startDate1 = LocalDateTime.now().plusDays(10);
        LocalDateTime endDate1 = startDate1.plusDays(5);

        LocalDateTime resPeriodStart = LocalDateTime.of(startDate1.toLocalDate(),startDate1.toLocalTime());
        LocalDateTime resPeriodEnd = resPeriodStart.plusDays(5);


        Address testCourseDto2Address = new Address("Poland","Wroclaw","Street2",12,"prop1");

        Course course1 = new Course("Name1","Description1",2000,"Cat1", testCourseDto2Address,startDate1,endDate1,
                0,14,resPeriodStart,resPeriodEnd);



        return courseMapper.mapToCourseDto(course1);
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

        courseDBService.saveCourse(courseMapper.mapToCourse(courseDto));

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
