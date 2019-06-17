package com.kdabrowski8712.sportcourseplannerbackend.service;

import com.kdabrowski8712.sportcourseplannerbackend.domain.Course;
import com.kdabrowski8712.sportcourseplannerbackend.repository.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseDBService {

    @Autowired
    private CourseDao courseDao;

    public Optional<Course> getCourse(Long id) {
        return courseDao.findById(id);
    }

}
