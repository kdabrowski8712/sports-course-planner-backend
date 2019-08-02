package com.kdabrowski8712.sportcourseplannerbackend.mapper;

import com.kdabrowski8712.sportcourseplannerbackend.domain.Address;
import com.kdabrowski8712.sportcourseplannerbackend.domain.Instructor;
import com.kdabrowski8712.sportcourseplannerbackend.domain.ScheduleEntry;
import com.kdabrowski8712.sportcourseplannerbackend.domain.ScheduleEntryDto;
import com.kdabrowski8712.sportcourseplannerbackend.service.InstructorDBService;
import com.kdabrowski8712.sportcourseplannerbackend.service.ScheduleEntryDBService;
import org.hibernate.validator.constraints.br.TituloEleitoral;
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
public class ScheduleEntryMapperTestSuite {

    @Autowired
    private InstructorDBService instructorDBService;
    @Autowired
    private ScheduleEntryDBService scheduleEntryDBService;
    @Autowired
    private ScheduleEntryMapper scheduleEntryMapper;

    private Instructor testInstructor;
    private ScheduleEntry testScheduleEntry;


    @Before
    public void preapreDB() {


        Address instructorAdress = new Address("Poland", "Wroclaw", "Zawiszy", 3,
                12, null);

        testInstructor = new Instructor("Jan", "Maj", "super", instructorAdress);

        LocalDateTime beg = LocalDateTime.now().plusHours(1);

        testScheduleEntry = new ScheduleEntry(beg, 60, false);
        testInstructor.getSchedule().add(testScheduleEntry);
        testScheduleEntry.setInstructor(testInstructor);

        instructorDBService.saveInstructor(testInstructor);
        scheduleEntryDBService.saveEntry(testScheduleEntry);

    }

    @After
    public void cleanDB() {

        scheduleEntryDBService.deleteEntry(testScheduleEntry.getId());
        instructorDBService.deleteInstructor(testInstructor.getId());
    }

    @Test
    public void testMapToScheduleentryDto() {

        //Given
        //when
        ScheduleEntryDto scheduleEntryDto = scheduleEntryMapper.mapToSchedulrEntryDto(testScheduleEntry);
        //then
        Assert.assertEquals(testInstructor.getId(), scheduleEntryDto.getInstructorId());

    }

    @Test
    public void testMapToScheduleEntry() {

        //Given
        ScheduleEntryDto scheduleEntryDto = new ScheduleEntryDto();
        scheduleEntryDto.setId(testScheduleEntry.getId());
        scheduleEntryDto.setInstructorId(testInstructor.getId());
        scheduleEntryDto.setStartHour(testScheduleEntry.getStartHour());
        scheduleEntryDto.setReserved(testScheduleEntry.isReserved());
        scheduleEntryDto.setDuration(testScheduleEntry.getDuration());
        //when

        ScheduleEntry scheduleEntry = scheduleEntryMapper.maptoScheduleEntry(scheduleEntryDto);
        //then
        Assert.assertEquals(testInstructor.getSurname(), scheduleEntry.getInstructor().getSurname());

    }
}
