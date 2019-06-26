package com.kdabrowski8712.sportcourseplannerbackend.service;

import com.kdabrowski8712.sportcourseplannerbackend.domain.PrivateOffer;
import com.kdabrowski8712.sportcourseplannerbackend.repository.IndividualTrainingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrivateOfferDBService {

    @Autowired
    private IndividualTrainingDao individualTrainingDao;

    public Optional<PrivateOffer> getOffer(Long id) {
        return individualTrainingDao.findById(id);
    }

    public PrivateOffer saveOffer(PrivateOffer offer) {
        return individualTrainingDao.save(offer);
    }

    public void deleteOffer(Long id) {
        individualTrainingDao.deleteById(id);
    }

}
