package com.kdabrowski8712.sportcourseplannerbackend.mapper;

import com.kdabrowski8712.sportcourseplannerbackend.domain.Instructor;
import com.kdabrowski8712.sportcourseplannerbackend.domain.Reservation;
import com.kdabrowski8712.sportcourseplannerbackend.domain.ScheduleEntry;
import com.kdabrowski8712.sportcourseplannerbackend.domain.ScheduleEntryDto;
import com.kdabrowski8712.sportcourseplannerbackend.service.InstructorDBService;
import com.kdabrowski8712.sportcourseplannerbackend.service.ReservationDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ScheduleEntryMapper {

    @Autowired
    private InstructorDBService instructorDBService;

    @Autowired
    private ReservationDBService reservationDBService;

    public ScheduleEntryDto mapToSchedulrEntryDto(ScheduleEntry scheduleEntry) {

        ScheduleEntryDto scheduleEntryDto = new ScheduleEntryDto();

        scheduleEntryDto.setDuration(scheduleEntry.getDuration());
        scheduleEntryDto.setId(scheduleEntry.getId());
        scheduleEntryDto.setReserved(scheduleEntry.isReserved());
        scheduleEntryDto.setStartHour(scheduleEntry.getStartHour());

        scheduleEntryDto.setInstructorId(scheduleEntry.getInstructor().getId());

        if(scheduleEntry.getReservation()!=null) {
            scheduleEntryDto.setReservationId(scheduleEntry.getReservation().getId());
        }


        return scheduleEntryDto;
    }

    public ScheduleEntry maptoScheduleEntry(ScheduleEntryDto scheduleEntryDto) {

        ScheduleEntry scheduleEntry = new ScheduleEntry();

        scheduleEntry.setDuration(scheduleEntryDto.getDuration());
        scheduleEntry.setId(scheduleEntryDto.getId());
        scheduleEntry.setReserved(scheduleEntryDto.isReserved());
        scheduleEntry.setStartHour(scheduleEntryDto.getStartHour());

        Optional<Instructor> instructor = instructorDBService.getInstructor(scheduleEntryDto.getInstructorId());
        scheduleEntry.setInstructor(instructor.get());


        if (scheduleEntryDto.getReservationId()!=null) {
            Optional<Reservation> reservation = reservationDBService.getReservation(scheduleEntryDto.getReservationId());
            scheduleEntry.setReservation(reservation.get());
        }

        return scheduleEntry;
    }

    public List<ScheduleEntryDto> mapToSchedulrEntryDtoList(List<ScheduleEntry> scheduleEntryList) {

        List<ScheduleEntryDto> scheduleEntryDtos = new ArrayList<>();

        scheduleEntryList.stream()
                .forEach(scheduleEntry -> {
                    scheduleEntryDtos.add(mapToSchedulrEntryDto(scheduleEntry));
                });

        return scheduleEntryDtos;
    }

    public List<ScheduleEntry> maptoScheduleEntryList(List<ScheduleEntryDto> scheduleEntryDtoLiat) {

        List<ScheduleEntry> scheduleEntries = new ArrayList<>();

        scheduleEntryDtoLiat.stream()
                .forEach(scheduleEntryDto -> {
                    scheduleEntries.add(maptoScheduleEntry(scheduleEntryDto));
                });

        return scheduleEntries;

    }

}
