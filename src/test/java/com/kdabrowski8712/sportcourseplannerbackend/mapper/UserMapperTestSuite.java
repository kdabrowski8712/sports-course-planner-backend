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
public class UserMapperTestSuite {

    @Autowired
    private ReservationDBService reservationDBService;

    @Autowired
    private InstructorDBService instructorDBService;

    @Autowired
    private CourseDBService courseDBService;

    @Autowired
    private UserDBService userDBService;

    @Autowired
    private UserMapper userMapper;

    private User testUser;
    private Reservation testReservation;
    private Course testCourse;
    private Reservation testReservation2;
    private Course testCourse2;
    private Instructor testInstructor;

    @Before
    public void preapreDB() {
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

    @After
    public void cleanDB() {
        reservationDBService.deleteReservation(testReservation.getId());
        courseDBService.deleteCourse(testCourse.getId());
        userDBService.deleteUser(testUser.getId());
        instructorDBService.deleteInstructor(testInstructor.getId());
    }

    @Test
    public void testMapToUserDto() {

        //Given
        //when
        UserDto userDto = userMapper.mapToUserDto(testUser);
        //then
        Assert.assertEquals(testReservation.getId(), userDto.getReservationIds().get(0));

    }

    @Test
    public void testMapToUser() {
        //Given
        UserDto userDto = new UserDto();
        userDto.setId(testUser.getId());
        userDto.setSurname(testUser.getSurname());
        userDto.setDescription(testUser.getName());
        userDto.setAddress(new Address(testUser.getAddress()));
        userDto.getReservationIds().add(testReservation.getId());
        //when

        User user = userMapper.mapToUser(userDto);
        //then
        Assert.assertEquals(testReservation.getId(), user.getReservations().get(0).getId());

    }

//    @Test
//    public void test1() {
//
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime start = now.plusDays(10);
//        LocalDateTime end  = start.plusDays(20);
//
//        LocalDateTime valid = now.plusDays(2);
//
//        Address courseAdress = new Address("Poland","Wroclaw","Ulica1",3,
//                12,"Property1");
//
//        Address courseAdress2= new Address("Poland","Wroclaw","Ulica2",3,
//                12,"Property2");
//
//        Address instructorAddress = new Address("Poland","Wroclaw","Kosciuszki",
//                1,2,null);
//
//        Address userAddress = new Address("Poland","Wroclaw","Jana",
//                33,45,null);
//
//        testCourse = new Course("Course 1","Desc 1",320,"Cat1",courseAdress
//                , start,end,0,10);
//        testCourse2  = new Course("Course 2","Desc 2",320,"Cat2",courseAdress
//                , start,end,0,10);
//
//        testInstructor = new Instructor("Jan","Kowalski","super",instructorAddress);
//        testUser = new User("Zbyszek","Jakis","user1",userAddress);
//
//        testReservation = new Reservation();
//        testReservation2 = new Reservation();
//
//        testReservation.setOwner(testUser);
//        testReservation.setCourse(testCourse);
//        testReservation.setValid(true);
//        testReservation.setValidUntil(valid);
//
//        testReservation2.setOwner(testUser);
//        testReservation2.setCourse(testCourse2);
//        testReservation2.setValid(true);
//        testReservation2.setValidUntil(valid);
//
//        testInstructor.addCourse(testCourse);
//        testInstructor.addCourse(testCourse2);
//
//        testCourse.getInstructors().add(testInstructor);
//        testCourse2.getInstructors().add(testInstructor);
//
//        testCourse.getReservations().add(testReservation);
//        testCourse2.getReservations().add(testReservation2);
//
//        testUser.getReservations().add(testReservation);
//        testUser.getReservations().add(testReservation2);
//
//        instructorDBService.saveInstructor(testInstructor);
//        userDBService.saveUser(testUser);
//        courseDBService.saveCourse(testCourse);
//        courseDBService.saveCourse(testCourse2);
//        reservationDBService.saveReservation(testReservation);
//        reservationDBService.saveReservation(testReservation2);
//
//        reservationDBService.deleteReservation(testReservation2.getId());
//
//    }
}
