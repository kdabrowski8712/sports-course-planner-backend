package com.kdabrowski8712.sportcourseplannerbackend.repository;

import com.kdabrowski8712.sportcourseplannerbackend.domain.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CourseDao extends CrudRepository<Course,Long> {
}