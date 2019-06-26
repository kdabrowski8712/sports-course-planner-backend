package com.kdabrowski8712.sportcourseplannerbackend.mapper;

import com.kdabrowski8712.sportcourseplannerbackend.domain.*;
import com.kdabrowski8712.sportcourseplannerbackend.service.InstructorDBService;
import com.kdabrowski8712.sportcourseplannerbackend.service.PrivateOfferDBService;
import com.kdabrowski8712.sportcourseplannerbackend.service.ReservationDBService;
import com.kdabrowski8712.sportcourseplannerbackend.service.UserDBService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrivateOfferMapperTestSuite {

    @Autowired
    private UserDBService userDBService;
    @Autowired
    private ReservationDBService reservationDBService;
    @Autowired
    private PrivateOfferDBService privateOfferDBService;
    @Autowired
    private InstructorDBService instructorDBService;
    @Autowired
    private PrivateOfferMapper privateOfferMapper;


    private User testUser;
    private Reservation testReservation;
    private PrivateOffer testPrivateOffer;
    private Instructor testInstructor;

    @Before
    public void prepareDB() {

        Address userAddress = new Address("Poland","Wroclaw","Jana",
                33,45,null);

        Address instructorAdress = new Address("Poland","Wroclaw","Zawiszy",3,
                12,null);

        Address privateOfferAdress = new Address("Poland","Wroclaw","Bema",3, null,"Crssfit 2");

        testInstructor = new Instructor("Jan","Maj","super",instructorAdress);

        testUser = new User("Zbyszek","Kuc","normal user",userAddress);

        testReservation = new Reservation();

        testPrivateOffer = new PrivateOffer("Streching","super st",35.0F,"Zajecia silowe",privateOfferAdress,60);

        testInstructor.getTrainingoffers().add(testPrivateOffer);

        testReservation = new Reservation();

        testReservation.setOwner(testUser);
        testReservation.setPrivateOffer(testPrivateOffer);
        testReservation.setValid(true);

        testPrivateOffer.setInstructor(testInstructor);
        testPrivateOffer.getReservations().add(testReservation);

        userDBService.saveUser(testUser);
        instructorDBService.saveInstructor(testInstructor);
        privateOfferDBService.saveOffer(testPrivateOffer);
        reservationDBService.saveReservation(testReservation);
    }

    @After
    public void cleanDB() {

        reservationDBService.deleteReservation(testReservation.getId());
        privateOfferDBService.deleteOffer(testPrivateOffer.getId());
        instructorDBService.deleteInstructor(testInstructor.getId());
        userDBService.deleteUser(testUser.getId());

    }

    @Test
    public void testPrivateOfferDtoMApping()
    {
        //Given
        //when
        PrivateOfferDto privateOfferDto = privateOfferMapper.mapToPrivateOfferDto(testPrivateOffer);
        //Then
        Assert.assertEquals(testInstructor.getId(),privateOfferDto.getInstructor_id());
        Assert.assertEquals(testReservation.getId(),privateOfferDto.getReservationIds().get(0));

    }

    @Test
    public void testPrivateOfferMapping() {

        //given
        Address privateOfferAdress = new Address("Poland","Wroclaw","Bema",3, null,"Crssfit 2");

        PrivateOfferDto privateOfferDto = new PrivateOfferDto();
        privateOfferDto.setId(testPrivateOffer.getId());
        privateOfferDto.setAddress(new Address(testPrivateOffer.getAddress()));
        privateOfferDto.setDuration(testPrivateOffer.getDuration());
        privateOfferDto.setInstructor_id(testInstructor.getId());
        privateOfferDto.setCategory(testPrivateOffer.getCategory());
        privateOfferDto.setDescription(testPrivateOffer.getDescription());
        privateOfferDto.setName(testPrivateOffer.getName());
        privateOfferDto.setPrice(testPrivateOffer.getPrice());

        privateOfferDto.getReservationIds().add(testReservation.getId());
        //When
        PrivateOffer privateOffer = privateOfferMapper.mapToOffer(privateOfferDto);

        //Then

        Assert.assertEquals(testInstructor.getSurname(),privateOffer.getInstructor().getSurname());
        Assert.assertEquals(testReservation.isValid(),privateOffer.getReservations().get(0).isValid());


    }
}
