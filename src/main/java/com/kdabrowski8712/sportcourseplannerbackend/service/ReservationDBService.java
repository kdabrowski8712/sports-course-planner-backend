package com.kdabrowski8712.sportcourseplannerbackend.service;

import com.kdabrowski8712.sportcourseplannerbackend.domain.Reservation;
import com.kdabrowski8712.sportcourseplannerbackend.repository.CourseDao;
import com.kdabrowski8712.sportcourseplannerbackend.repository.ReservationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationDBService {

    @Autowired
    private ReservationDao reservationDao;

    public Optional<Reservation> getReservation(Long id) {
        return reservationDao.findById(id);

    }

    public Reservation saveReservation(final Reservation reservation) {
        return reservationDao.save(reservation);
    }

    public void deleteReservation(long id) {
        reservationDao.deleteById(id);
    }
}
