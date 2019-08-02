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
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseMapperTestSuite {

    @Autowired
    private ReservationDBService reservationDBService;

    @Autowired
    private InstructorDBService instructorDBService;

    @Autowired
    private CourseDBService courseDBService;

    @Autowired
    private UserDBService userDBService;

    @Autowired
    private CourseMapper courseMapper;

    private User testUser;
    private Reservation testReservation;
    private Course testCourse;
    private Instructor testInstructor;

    @Before
    public void dbPrepActions() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.plusDays(10);
        LocalDateTime end = start.plusDays(20);

        LocalDateTime resPeriodStart = LocalDateTime.of(now.toLocalDate(), now.toLocalTime());
        LocalDateTime resPeriodEnd = resPeriodStart.plusDays(5);

        LocalDateTime valid = now.plusDays(2);

        Address testAdress = new Address("Poland", "Wroclaw", "Glowna", 3,
                12, "Szkola Tanca Skok");

        Address instructorAddress = new Address("Poland", "Wroclaw", "Kosciuszki",
                1, 2, null);

        Address userAddress = new Address("Poland", "Wroclaw", "Jana",
                33, 45, null);

        testCourse = new Course("Zumba 1", "SuperZumba", 320, "Taniec", testAdress
                , start, end, 0, 10, resPeriodStart, resPeriodEnd);

        testInstructor = new Instructor("Jan", "Kowalski", "super", instructorAddress);
        testUser = new User("Zbyszek", "Jakis", "user1", userAddress);

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

    @Test
    public void mapToDtoTest() {

        //Given
        //When

        CourseDto mappedDto = courseMapper.mapToCourseDto(testCourse);

        //Then

        Assert.assertEquals(testInstructor.getId(), mappedDto.getInstructorsIds().get(0));
        Assert.assertEquals(testReservation.getId(), mappedDto.getReservationsIds().get(0));

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
    public void mapToCourse() {

        //Given

        CourseDto mappedCourse = new CourseDto();
        mappedCourse.setId(testCourse.getId());
        mappedCourse.setEndDate(testCourse.getEndDate());
        mappedCourse.setStartDate(testCourse.getStartDate());
        mappedCourse.setMaxNrOfUsers(testCourse.getMaxNrOfUsers());
        mappedCourse.setMinNrOfUsers(testCourse.getMinNrOfUsers());
        mappedCourse.setCategory(testCourse.getCategory());
        mappedCourse.setDescription(testCourse.getDescription());
        mappedCourse.setName(testCourse.getName());
        mappedCourse.setAddress(new Address(testCourse.getAddress()));
        mappedCourse.getInstructorsIds().add(testInstructor.getId());
        mappedCourse.getReservationsIds().add(testReservation.getId());

        //When

        Course course = courseMapper.mapToCourse(mappedCourse);

        //Then

        Assert.assertEquals("Kowalski", course.getInstructors().get(0).getSurname());
        Assert.assertEquals(testUser.getId(), course.getReservations().get(0).getOwner().getId());

        instructorDBService.saveInstructor(testInstructor);
        userDBService.saveUser(testUser);
        courseDBService.saveCourse(testCourse);
        reservationDBService.saveReservation(testReservation);

    }

}
