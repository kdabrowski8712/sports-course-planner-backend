package com.kdabrowski8712.sportcourseplannerbackend.repository;

import com.kdabrowski8712.sportcourseplannerbackend.domain.Address;
import com.kdabrowski8712.sportcourseplannerbackend.domain.Reservation;
import com.kdabrowski8712.sportcourseplannerbackend.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTestSuite {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ReservationDao reservationDao;


}
