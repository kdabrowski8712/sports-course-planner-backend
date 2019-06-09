package com.kdabrowski8712.sportcourseplannerbackend.mapper;

import com.kdabrowski8712.sportcourseplannerbackend.domain.Address;
import com.kdabrowski8712.sportcourseplannerbackend.domain.Course;
import com.kdabrowski8712.sportcourseplannerbackend.domain.CourseDto;

import java.util.ArrayList;
import java.util.List;

public class CourseMapper {

    public Course mapToCourse(final CourseDto courseDto) {

        Address transformedAddress = new Address(courseDto.getAddress().getCountry(),courseDto.getAddress().getTown(),
                                                courseDto.getAddress().getStreet(),courseDto.getAddress().getBuildingNr(),
                                                courseDto.getAddress().getHouseNr(),courseDto.getAddress().getPropertyName());


        Course transformedCourse = new Course(courseDto.getName(),courseDto.getDescription(),courseDto.getPrice()
                                            ,courseDto.getCategory(),transformedAddress,courseDto.getStartDate(),
                                            courseDto.getEndDate(),courseDto.getMinNrOfUsers(),courseDto.getMaxNrOfUsers());

        return transformedCourse;

    }

    public CourseDto mapToCourseDto(final Course course) {

        Address transformedAddress = new Address(course.getAddress().getCountry(),course.getAddress().getTown(),
                course.getAddress().getStreet(),course.getAddress().getBuildingNr(),
                course.getAddress().getHouseNr(),course.getAddress().getPropertyName());



        CourseDto transformedCourseDto = new CourseDto(course.getName(),course.getDescription(),course.getPrice()
                ,course.getCategory(),transformedAddress,course.getStartDate(),
                course.getEndDate(),course.getMinNrOfUsers(),course.getMaxNrOfUsers());

        return  transformedCourseDto;

    }

    public List<CourseDto> mapToCourseDtoList(final List<Course> courses) {




        return new ArrayList<>();

    }


}
