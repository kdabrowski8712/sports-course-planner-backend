package com.kdabrowski8712.sportcourseplannerbackend.repository;

import com.kdabrowski8712.sportcourseplannerbackend.domain.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CourseDao extends CrudRepository<Course,Long> {

    Optional<Course> findById(Long id);

    @Override
    List<Course> findAll();
}
