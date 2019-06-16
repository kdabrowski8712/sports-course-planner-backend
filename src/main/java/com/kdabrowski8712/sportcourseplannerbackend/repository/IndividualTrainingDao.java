package com.kdabrowski8712.sportcourseplannerbackend.repository;

import com.kdabrowski8712.sportcourseplannerbackend.domain.IndividualTrainingOffer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface IndividualTrainingDao extends CrudRepository<IndividualTrainingOffer,Long> {
}
