package com.kdabrowski8712.sportcourseplannerbackend.controller;

import com.google.gson.Gson;
import com.kdabrowski8712.sportcourseplannerbackend.domain.*;
import com.kdabrowski8712.sportcourseplannerbackend.mapper.CourseMapper;
import com.kdabrowski8712.sportcourseplannerbackend.mapper.InstructorMapper;
import com.kdabrowski8712.sportcourseplannerbackend.service.InstructorDBService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InstructorController.class)
public class InstructorControllerTestSuite {

    @MockBean
    private InstructorDBService instructorDBService;

    @MockBean
    private InstructorMapper instructorMapper;

     @MockBean
    private CourseMapper courseMapper;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getInstructorTest() throws Exception {


        Address  testAddress = new Address("Poland","Wroclaw","Kukuczki",12,
                null,null);
        Address secondTestAddress = new Address(testAddress);

        Instructor testInstructor = new Instructor("TestIn1","Sur","TestDesc",testAddress);
        testInstructor.setId(1L);
        Optional<Instructor> optionalInstructor = Optional.of(testInstructor);

        InstructorDto testInstructorDto = new InstructorDto("TestIn1","Sur",
                "TestDesc",secondTestAddress);
        testInstructorDto.setId(1L);


        when(instructorDBService.getInstructor(anyLong())).thenReturn(optionalInstructor);
        when(instructorMapper.mapToInstructorDto(optionalInstructor.get())).thenReturn(testInstructorDto);

        mockMvc.perform(get("/apiv1/instructors/{instructorId}","1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.name",is("TestIn1")))
                .andExpect(jsonPath("$.surname",is("Sur")))
                .andExpect(jsonPath("$.address.country",is("Poland")));
    }

    @Test
    public void testDelete() throws Exception{

        Long id =1L;

        mockMvc.perform(delete("/apiv1/instructors/{instructorId}",id)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testUpdateInstructor() throws Exception {

        Address  testAddress = new Address("Poland","Wroclaw","Kukuczki",12,
                null,null);
        Address secondTestAddress = new Address(testAddress);

        InstructorDto instructorDto = new InstructorDto("TestIn1","Updated Surname",
                "TestDesc",secondTestAddress);
        instructorDto.setId(456L);

        Instructor instructor= new Instructor("TestIn1","Updated Surname","TestDesc",testAddress);
        instructor.setId(456L);

        when(instructorMapper.mapToInstructor(any(InstructorDto.class))).thenReturn(instructor);
        when(instructorDBService.saveInstructor(any(Instructor.class))).thenReturn(instructor);
        when(instructorMapper.mapToInstructorDto(any(Instructor.class))).thenReturn(instructorDto);

        Gson g = new Gson();
        String jsonContent = g.toJson(instructorDto);

        mockMvc.perform(put("/apiv1/instructors")
                .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .content(jsonContent))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.surname",is("Updated Surname")));
    }


    @Test
    public void testCreateInstructor() throws Exception {

        Address  testAddress = new Address("Poland","Wroclaw","Kukuczki",12,
                null,null);
        Address secondTestAddress = new Address(testAddress);

        InstructorDto instructorDto = new InstructorDto("TestIn1","Sur",
                "TestDesc",secondTestAddress);
        instructorDto.setId(123L);

        Instructor instructor= new Instructor("TestIn1","Sur","TestDesc",testAddress);
        instructor.setId(123L);

        when(instructorMapper.mapToInstructor(any(InstructorDto.class))).thenReturn(instructor);
        when(instructorMapper.mapToInstructorDto(any(Instructor.class))).thenReturn(instructorDto);
        when(instructorDBService.saveInstructor(any(Instructor.class))).thenReturn(instructor);


        Gson g = new Gson();
        String jsonContent = g.toJson(instructorDto);

        mockMvc.perform(post("/apiv1/instructors")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .content(jsonContent))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id",is(123)));

    }

    @Test
    public void testGetCoursesForInstructor() throws Exception {

        //Given
        Address  testAddress = new Address("Poland","Wroclaw","Kukuczki",12,
                null,null);
        Address testAddressDto = new Address(testAddress);

        Address  testCourse1Address = new Address("Poland","Wroclaw","Street1",12,
                null,"Propwety1");

        Address testCourse2Address = new Address("Poland","Wroclaw","Street2",12,
                null,"Property2");

        Address  testCourseDto1Address = new Address("Poland","Wroclaw","Street1",12,
                null,"Propwety1");

        Address testCourseDto2Address = new Address("Poland","Wroclaw","Street2",12,
                null,"Property2");

        Instructor instructor= new Instructor("TestIn1","Sur","TestDesc",testAddress);
        instructor.setId(512L);

        Optional<Instructor> optionalInstructor = Optional.of(instructor);

        LocalDateTime startDate1 = LocalDateTime.now().plusDays(10);
        LocalDateTime endDate1 = startDate1.plusDays(5);

        LocalDateTime startDate2 = LocalDateTime.now().plusDays(15);
        LocalDateTime endDate2 = startDate2.plusDays(15);

        LocalDateTime startDateDto1 = LocalDateTime.now().plusDays(10);
        LocalDateTime endDateDto1 = startDate1.plusDays(5);

        LocalDateTime startDateDto2 = LocalDateTime.now().plusDays(15);
        LocalDateTime endDateDto2 = startDate2.plusDays(15);


        LocalDateTime resPeriodStartCourse1 = LocalDateTime.of(startDate1.toLocalDate(),startDate1.toLocalTime());
        LocalDateTime resPeriodEndCourse1 = resPeriodStartCourse1.plusDays(5);

        LocalDateTime resPeriodStartCourse2 = LocalDateTime.of(startDate2.toLocalDate(),startDate2.toLocalTime());
        LocalDateTime resPeriodEndCourse2 = resPeriodStartCourse2.plusDays(5);


        LocalDateTime resPeriodStartDto1 = LocalDateTime.of(startDateDto1.toLocalDate(),startDateDto1.toLocalTime());
        LocalDateTime resPeriodEndDto1 = resPeriodStartDto1.plusDays(5);

        LocalDateTime resPeriodStartDto2 = LocalDateTime.of(startDateDto2.toLocalDate(),startDateDto2.toLocalTime());
        LocalDateTime resPeriodEndDto2 = resPeriodStartDto2.plusDays(5);





        InstructorDto instructorDto = new InstructorDto("TestIn1","Sur",
                "TestDesc",testAddressDto);
        instructorDto.setId(512L);

        Course course1 = new Course("Name1","Description1",2000,"Cat1",
                testCourse1Address,startDate1,endDate1,0,14,
                resPeriodStartCourse1,resPeriodEndCourse1);

        course1.setId(144L);

        Course course2 = new Course("Name2","Description2",3000,"Cat2",
                testCourse2Address,startDate2,endDate2,0,10,
                resPeriodStartCourse2,resPeriodEndCourse2);

        course2.setId(155L);


        CourseDto courseDto1 = new CourseDto(144L,"Name1","Description1",2000,
                "Cat1",testCourseDto1Address,
                startDateDto1,endDateDto1,0,14,
                resPeriodStartDto1,resPeriodEndDto1);

        CourseDto courseDto2 = new CourseDto(155L,"Name2","Description2",3000,
                "Cat2",testCourseDto2Address,
                startDateDto2,endDateDto2,0,10,
                resPeriodStartDto2,resPeriodEndDto2);


        instructor.getCourses().add(course1);
        instructor.getCourses().add(course2);

        course1.getInstructors().add(instructor);
        course2.getInstructors().add(instructor);

        when(instructorDBService.getInstructor(anyLong())).thenReturn(optionalInstructor);
        when(courseMapper.mapToCourseDto(course1)).thenReturn(courseDto1);
        when(courseMapper.mapToCourseDto(course2)).thenReturn(courseDto2);

        mockMvc.perform(get("/apiv1/instructors/{instructorId}/courses","512")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id",is(144)))
        .andExpect(jsonPath("$[0].name",is("Name1")))
        .andExpect(jsonPath("$[1].id",is(155)))
        .andExpect((jsonPath("$[1].name",is("Name2"))));

    }


}
