package com.kdabrowski8712.sportcourseplannerbackend.mapper;

import com.kdabrowski8712.sportcourseplannerbackend.domain.*;
import com.kdabrowski8712.sportcourseplannerbackend.service.InstructorDBService;
import com.kdabrowski8712.sportcourseplannerbackend.service.ReservationDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CourseMapper {

    @Autowired
    private ReservationDBService reservationDBService;

    @Autowired
    private InstructorDBService instructorDBService;

    public Course mapToCourse(final CourseDto courseDto) {

        Address transformedAddress = new Address(courseDto.getAddress());

        Course transformedCourse = new Course(courseDto.getName(), courseDto.getDescription(), courseDto.getPrice()
                , courseDto.getCategory(), transformedAddress, courseDto.getStartDate(),
                courseDto.getEndDate(), courseDto.getMinNrOfUsers(),
                courseDto.getMaxNrOfUsers(), courseDto.getReservationPeriodStart(),
                courseDto.getReservationPeriodEnd());

        transformedCourse.setId(courseDto.getId());

        courseDto.getReservationsIds().stream()
                .forEach(resId -> {
                    Optional<Reservation> reservation = reservationDBService.getReservation(resId);
                    if (reservation.isPresent()) {
                        transformedCourse.getReservations().add(reservation.get());
                    }
                });

        courseDto.getInstructorsIds().stream()
                .forEach(instructorId -> {
                    Optional<Instructor> instructor = instructorDBService.getInstructor(instructorId);
                    if (instructor.isPresent()) {
                        transformedCourse.getInstructors().add(instructor.get());
                    }
                });

        return transformedCourse;
    }


    public CourseDto mapToCourseDto(final Course course) {

        Address transformedAddress = new Address(course.getAddress());

        CourseDto transformedCourseDto = new CourseDto(course.getId(), course.getName(), course.getDescription(),
                course.getPrice(), course.getCategory(), transformedAddress, course.getStartDate(),
                course.getEndDate(), course.getMinNrOfUsers(), course.getMaxNrOfUsers(),
                course.getReservation_period_start(), course.getReservation_period_end());

        transformedCourseDto.setId(course.getId());

        course.getInstructors().stream()
                .forEach(instructor -> {
                    transformedCourseDto.getInstructorsIds().add(instructor.getId());
                });

        course.getReservations().stream()
                .forEach(reservation -> {
                    transformedCourseDto.getReservationsIds().add(reservation.getId());
                });

        return transformedCourseDto;

    }

    public List<CourseDto> mapToCourseDtoList(final List<Course> courses) {

        List<CourseDto> result = new ArrayList<>();

        courses.stream()
                .forEach(course -> {
                    result.add(mapToCourseDto(course));
                });

        return result;

    }

    public List<Course> mapToCourselist(final List<CourseDto> courseDtos) {

        List<Course> result = new ArrayList<>();

        courseDtos.stream()
                .forEach(courseDto -> {
                    result.add(mapToCourse(courseDto));
                });

        return result;

    }


}
