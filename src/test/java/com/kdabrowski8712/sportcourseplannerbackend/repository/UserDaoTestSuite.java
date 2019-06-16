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

    @Test
    public void testUserDaoSave() {

        //Given
        Address address = new Address("12","13","14",1,2,"15");
        User user = new User("jan","kowalski","test1",address);

        Reservation reservation = new Reservation();
        reservation.setOwner(user);

        reservationDao.save(reservation);
        //userDao.save(user);

        Long user_id = user.getId();
        Long reservstion_id = reservation.getId();

        Optional<User> readUser = userDao.findById(user_id);
        Optional<Reservation> readReservation = reservationDao.findById(reservstion_id);

        Assert.assertTrue(readUser.isPresent());
        Assert.assertTrue(readReservation.isPresent());



       // userDao.deleteById(id);


    }


}
