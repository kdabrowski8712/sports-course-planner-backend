package com.kdabrowski8712.sportcourseplannerbackend.mapper;

import com.kdabrowski8712.sportcourseplannerbackend.domain.*;
import com.kdabrowski8712.sportcourseplannerbackend.service.CourseDBService;
import com.kdabrowski8712.sportcourseplannerbackend.service.InstructorDBService;
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

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationMapperTestSuite {

    @Autowired
    private InstructorDBService instructorDBService;
    @Autowired
    private CourseDBService courseDBService;
    @Autowired
    private UserDBService userDBService;
    @Autowired
    private ReservationDBService reservationDBService;
    @Autowired
    private ReservationMapper reservationMapper;

    private Instructor testInstructor;
    private Course testCourse;
    private User testUser;
    private Reservation testReservation;

    @Before
    public void preapreDB() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.plusDays(10);
        LocalDateTime end  = start.plusDays(20);

        LocalDateTime resPeriodStart = LocalDateTime.of(now.toLocalDate(),now.toLocalTime());
        LocalDateTime resPeriodEnd = resPeriodStart.plusDays(5);


        LocalDateTime valid = now.plusDays(2);

        Address testAdress = new Address("Poland","Wroclaw","Glowna",3,
                12,"Szkola Tanca Skok");

        Address instructorAddress = new Address("Poland","Wroclaw","Kosciuszki",
                1,2,null);

        Address userAddress = new Address("Poland","Wroclaw","Jana",
                33,45,null);

        testCourse = new Course("Zumba 1","SuperZumba",320,"Taniec",testAdress
                , start,end,0,10,resPeriodStart,resPeriodEnd);

        testInstructor = new Instructor("Jan","Kowalski","super",instructorAddress);
        testUser = new User("Zbyszek","Jakis","user1",userAddress);

        testReservation = new Reservation();

        testReservation.setOwner(testUser);
        testReservation.setCourse(testCourse);
        testReservation.setValid(true);
        testReservation.setValidUntil(valid);

        testInstructor.addCourse(testCourse);
        testCourse.getInstructors().add(testInstructor);
        testCourse.getReservations().add(testReservation);

        testUser.getReservations().add(testReservation);

        instructorDBService.saveInstructor(testInstructor);
        userDBService.saveUser(testUser);
        courseDBService.saveCourse(testCourse);
        reservationDBService.saveReservation(testReservation);
    }

    @After
    public void cleanDB() {
        reservationDBService.deleteReservation(testReservation.getId());
        courseDBService.deleteCourse(testCourse.getId());
        userDBService.deleteUser(testUser.getId());
        instructorDBService.deleteInstructor(testInstructor.getId());
    }

    @Test
    public void testMapToReservationDto() {

        //Given
        //when
        ReservationDto reservationDto = reservationMapper.mapToReservationDto(testReservation);
        //then

        Assert.assertEquals(testReservation.getCourse().getId(),reservationDto.getCourseId());
        Assert.assertEquals(testUser.getId(),reservationDto.getOwnerId());

    }

    @Test
    public void testMapToReservation() {

        ReservationDto reservationDto = new ReservationDto();

        //Given
        reservationDto.setId(testReservation.getId());
        reservationDto.setValidUntil(testReservation.getValidUntil());
        reservationDto.setValid(testReservation.isValid());
        reservationDto.setOwnerId(testUser.getId());
        reservationDto.setCourseId(testCourse.getId());

        //when
        Reservation reservation = reservationMapper.mapToReservation(reservationDto);
        //then
        Assert.assertEquals(testUser.getName(),reservation.getOwner().getName());
        Assert.assertEquals(testCourse.getCategory(),reservation.getCourse().getCategory());


    }

}
