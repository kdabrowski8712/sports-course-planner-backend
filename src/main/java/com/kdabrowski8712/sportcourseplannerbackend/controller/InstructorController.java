package com.kdabrowski8712.sportcourseplannerbackend.controller;

import com.kdabrowski8712.sportcourseplannerbackend.domain.Address;
import com.kdabrowski8712.sportcourseplannerbackend.domain.CourseDto;
import com.kdabrowski8712.sportcourseplannerbackend.domain.Instructor;
import com.kdabrowski8712.sportcourseplannerbackend.domain.InstructorDto;
import com.kdabrowski8712.sportcourseplannerbackend.exceptions.InstructorNotFoundException;
import com.kdabrowski8712.sportcourseplannerbackend.mapper.CourseMapper;
import com.kdabrowski8712.sportcourseplannerbackend.mapper.InstructorMapper;
import com.kdabrowski8712.sportcourseplannerbackend.service.CourseDBService;
import com.kdabrowski8712.sportcourseplannerbackend.service.InstructorDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/apiv1")
public class InstructorController  {

    @Autowired
    private InstructorDBService instructorDBService;

    @Autowired
    private InstructorMapper instructorMapper;

    @Autowired
    private CourseMapper courseMapper;

    @GetMapping("/instructors/{instructorId}")
    public InstructorDto getInstructor(@PathVariable Long instructorId) throws InstructorNotFoundException {

        return instructorMapper.mapToInstructorDto(instructorDBService.getInstructor(instructorId).orElseThrow(
                InstructorNotFoundException::new
        ));
    }

    @GetMapping("/instructors/{instructorId}/courses")
    public List<CourseDto> getCoursesForInstructor(@PathVariable Long instructorId) throws InstructorNotFoundException {
        List<CourseDto> courseDtoList = new ArrayList<>();

        Optional<Instructor> potentialInstructor = instructorDBService.getInstructor(instructorId);
        if(potentialInstructor.isPresent()) {

            potentialInstructor.get().getCourses().stream()
                    .forEach(course -> {
                        courseDtoList.add(courseMapper.mapToCourseDto(course));
                    });
        }

        return courseDtoList;
    }

    @PostMapping(value = "/instructors", consumes = APPLICATION_JSON_VALUE)
    public InstructorDto addInstructor( @RequestBody  InstructorDto input) {

        Instructor toSave = instructorMapper.mapToInstructor(input);
        instructorDBService.saveInstructor(toSave);

        return instructorMapper.mapToInstructorDto(toSave);

    }

    @PutMapping(value = "/instructors")
    public InstructorDto updateInstructor(@RequestBody InstructorDto instructorDto) {

        Instructor toUpdate = instructorMapper.mapToInstructor(instructorDto);
        instructorDBService.saveInstructor(toUpdate);

        return instructorMapper.mapToInstructorDto(toUpdate);
    }

    @DeleteMapping(value = "/instructors/{instructorId}")
    public void removeInstructor(@PathVariable Long instructorId) {

        instructorDBService.deleteInstructor(instructorId);
    }



}
