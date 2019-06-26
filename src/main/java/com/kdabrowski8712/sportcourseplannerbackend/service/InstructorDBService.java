package com.kdabrowski8712.sportcourseplannerbackend.service;

import com.kdabrowski8712.sportcourseplannerbackend.domain.Instructor;
import com.kdabrowski8712.sportcourseplannerbackend.repository.InstructorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@Service
public class InstructorDBService {

    @Autowired
    private InstructorDao instructorDao;

    public Optional<Instructor> getInstructor(Long id) {
        return instructorDao.findById(id);
    }

    public Instructor saveInstructor(final Instructor instructor) {
        return  instructorDao.save(instructor);
    }

    public void deleteInstructor(Long id) {
        instructorDao.deleteById(id);
    }

}
