package com.kdabrowski8712.sportcourseplannerbackend.mapper;

import com.kdabrowski8712.sportcourseplannerbackend.domain.*;
import com.kdabrowski8712.sportcourseplannerbackend.service.CourseDBService;
import com.kdabrowski8712.sportcourseplannerbackend.service.ScheduleEntryDBService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InstructorMapper {

    @Autowired
    private ScheduleEntryDBService scheduleEntryDBService;

    @Autowired
    private CourseDBService courseDBService;

    public Instructor mapToInstructor(InstructorDto instructorDto) {

        Instructor mappedInstructor = new Instructor(instructorDto.getName(),
                instructorDto.getSurname(),instructorDto.getDescription(),
                new Address(instructorDto.getAddress()));

        instructorDto.getScheduleIds().stream()
                .forEach(scheduleId -> {
                            Optional<ScheduleEntry> scheduleEntry = scheduleEntryDBService.getScheduleEntry(scheduleId);
                            if(scheduleEntry.isPresent()) {
                                mappedInstructor.getSchedule().add(scheduleEntry.get());
                            }
                        }
                );

        instructorDto.getCoursesIds().stream()
                .forEach(courseId -> {
                    Optional<Course> course = courseDBService.getCourse(courseId);
                    if(course.isPresent()) {
                        mappedInstructor.getCourses().add(course.get());
                    }
                });

        return mappedInstructor;
    }

    public InstructorDto mapToInstructorDto(Instructor instructor) {

        InstructorDto mappedInstructorDto = new InstructorDto(instructor.getName(),
                instructor.getSurname(),instructor.getDescription(),
                new Address(instructor.getAddress()));

        instructor.getCourses().stream()
                .forEach(course -> {
                    mappedInstructorDto.getCoursesIds().add(course.getId());
                });

        instructor.getSchedule().stream()
                .forEach(scheduleEntry -> {
                    mappedInstructorDto.getScheduleIds().add(scheduleEntry.getId());
                });

        return mappedInstructorDto;
    }

    public List<InstructorDto> mapToInstructorDtoList(List<Instructor> instructors ) {

        List<InstructorDto> result = new ArrayList<>();

        instructors.stream()
                .forEach(instructor -> {
                    result.add(mapToInstructorDto(instructor));
                });

        return  result;

    }

    public List<Instructor> mapToInstructorList(List<InstructorDto> instructorDtos) {

        List<Instructor> result = new ArrayList<>();

        instructorDtos.stream()
                .forEach(instructorDto -> {
                    result.add(mapToInstructor(instructorDto));
                });

        return result;

    }
}
