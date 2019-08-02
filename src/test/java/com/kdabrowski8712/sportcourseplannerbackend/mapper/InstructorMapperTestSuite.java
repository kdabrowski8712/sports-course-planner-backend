package com.kdabrowski8712.sportcourseplannerbackend.mapper;

import com.kdabrowski8712.sportcourseplannerbackend.domain.*;
import com.kdabrowski8712.sportcourseplannerbackend.service.CourseDBService;
import com.kdabrowski8712.sportcourseplannerbackend.service.PrivateOfferDBService;
import com.kdabrowski8712.sportcourseplannerbackend.service.InstructorDBService;
import com.kdabrowski8712.sportcourseplannerbackend.service.ScheduleEntryDBService;
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
public class InstructorMapperTestSuite {

    @Autowired
    private ScheduleEntryDBService scheduleEntryDBService;

    @Autowired
    private PrivateOfferDBService privateOfferDBService;

    @Autowired
    private CourseDBService courseDBService;

    @Autowired
    private InstructorDBService instructorDBService;

    @Autowired
    private InstructorMapper instructorMapper;

    private Course testCourse;
    private PrivateOffer testOffer;
    private ScheduleEntry testEntry;
    private Instructor testInstructor;

    @Before
    public void prepareDB() {


        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.plusDays(15);
        LocalDateTime end = start.plusDays(30);

        LocalDateTime resPeriodStart = LocalDateTime.of(now.toLocalDate(), now.toLocalTime());
        LocalDateTime resPeriodEnd = resPeriodStart.plusDays(5);


        Address courseAdress = new Address("Poland", "Wroclaw", "Bema", 3,
                121, "Plywalnia Zbik");

        Address individualAdress = new Address("Poland", "Wroclaw", "Chelmonskiego", 3,
                null, "Basen SP34");

        Address instructorAdress = new Address("Poland", "Wroclaw", "Zawiszy", 3,
                12, null);


        testCourse = new Course("Kraul TI", "ekstra kraul", 350, "Plywanie",
                courseAdress, start, end, 0, 4, resPeriodStart, resPeriodEnd);

        testOffer = new PrivateOffer("indyw kraul", "indyw desc", 24.0F,
                "Plywanie", individualAdress, 60);


        testInstructor = new Instructor("Jan", "Maj", "super", instructorAdress);

        LocalDateTime beg = LocalDateTime.now().plusHours(1);

        testEntry = new ScheduleEntry(beg, 60, false);

        testOffer.setInstructor(testInstructor);
        testInstructor.getTrainingoffers().add(testOffer);

        testInstructor.getCourses().add(testCourse);
        testCourse.getInstructors().add(testInstructor);

        testInstructor.getSchedule().add(testEntry);
        testEntry.setInstructor(testInstructor);


        instructorDBService.saveInstructor(testInstructor);
        privateOfferDBService.saveOffer(testOffer);
        courseDBService.saveCourse(testCourse);
        scheduleEntryDBService.saveEntry(testEntry);


    }

    @After
    public void cleanDB() {

        scheduleEntryDBService.deleteEntry(testEntry.getId());
        courseDBService.deleteCourse(testCourse.getId());
        privateOfferDBService.deleteOffer(testOffer.getId());
        instructorDBService.deleteInstructor(testInstructor.getId());

    }

    @Test
    public void testMapToDto() {
        //Given
        //When

        InstructorDto mappedInstructor = instructorMapper.mapToInstructorDto(testInstructor);

        //Then

        Assert.assertEquals(testCourse.getId(), mappedInstructor.getCoursesIds().get(0));
        Assert.assertEquals(testOffer.getId(), mappedInstructor.getOfferIds().get(0));
        Assert.assertEquals(testEntry.getId(), mappedInstructor.getScheduleIds().get(0));
    }

    @Test
    public void testMapToInstructor() {

        //Given

        InstructorDto instructorDto = new InstructorDto();

        Address instAddr = new Address(testInstructor.getAddress());
        instructorDto.setId(testInstructor.getId());
        instructorDto.setName(testInstructor.getName());
        instructorDto.setDescription(testInstructor.getDescription());
        instructorDto.setSurname(testInstructor.getSurname());
        instructorDto.setAddress(instAddr);

        instructorDto.getOfferIds().add(testOffer.getId());
        instructorDto.getCoursesIds().add(testCourse.getId());
        instructorDto.getScheduleIds().add(testEntry.getId());

        //When

        Instructor instructor = instructorMapper.mapToInstructor(instructorDto);

        //Then

        Assert.assertEquals("indyw kraul", instructor.getTrainingoffers().get(0).getName());
        Assert.assertEquals("Kraul TI", instructor.getCourses().get(0).getName());
        Assert.assertEquals(60, instructor.getSchedule().get(0).getDuration());


    }
}
