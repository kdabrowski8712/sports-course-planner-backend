package com.kdabrowski8712.sportcourseplannerbackend.mapper;

import com.kdabrowski8712.sportcourseplannerbackend.domain.*;
import com.kdabrowski8712.sportcourseplannerbackend.service.CourseDBService;
import com.kdabrowski8712.sportcourseplannerbackend.service.PrivateOfferDBService;
import com.kdabrowski8712.sportcourseplannerbackend.service.ScheduleEntryDBService;
import com.kdabrowski8712.sportcourseplannerbackend.service.UserDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ReservationMapper {

    @Autowired
    private CourseDBService courseDBService;
    @Autowired
    private PrivateOfferDBService privateOfferDBService;
    @Autowired
    private UserDBService userDBService;
    @Autowired
    private ScheduleEntryDBService scheduleEntryDBService;


    public ReservationDto mapToReservationDto(Reservation reservation) {

        ReservationDto reservationDto = new ReservationDto();

        reservationDto.setId(reservation.getId());

        if(reservation.getCourse()!=null) {
            reservationDto.setCourseId(reservation.getCourse().getId());
        }
        if(reservation.getPrivateOffer() !=null) {
            reservationDto.setPrivateOfferId(reservation.getPrivateOffer().getId());
        }

        reservationDto.setOwnerId(reservation.getOwner().getId());

        if(reservation.getScheduleEntry()!=null) {
            reservationDto.setScheduleEntryId(reservation.getScheduleEntry().getId());
        }

        reservationDto.setValid(reservation.isValid());
        reservationDto.setValidUntil(reservation.getValidUntil());

        return reservationDto;
    }

    public Reservation mapToReservation(ReservationDto reservationDto) {

        Reservation reservation = new Reservation();

        reservation.setId(reservationDto.getId());

        if(reservationDto.getCourseId() !=null) {
            Optional<Course> course = courseDBService.getCourse(reservationDto.getCourseId());
            reservation.setCourse(course.get());
        }

        if(reservationDto.getPrivateOfferId() !=null) {
            Optional<PrivateOffer> privateOffer = privateOfferDBService.getOffer(reservationDto.getPrivateOfferId());
            reservation.setPrivateOffer(privateOffer.get());
        }

        Optional<User> user = userDBService.getUser(reservationDto.getOwnerId());
        reservation.setOwner(user.get());

        if(reservationDto.getScheduleEntryId() !=null) {
            Optional<ScheduleEntry> scheduleEntry = scheduleEntryDBService.getScheduleEntry(reservationDto.getScheduleEntryId());
            reservation.setScheduleEntry(scheduleEntry.get());
        }

        reservation.setValid(reservationDto.isValid());
        reservation.setValidUntil(reservationDto.getValidUntil());

        return reservation;
    }

    public List<ReservationDto> mapToReservationDtoList(List<Reservation> reservations) {
        List<ReservationDto> result = new ArrayList<>();

        reservations.stream()
                .forEach(reservation -> {
                    result.add(mapToReservationDto(reservation));
                });

        return result;
    }

    public List<Reservation> mapToReservationList(List<ReservationDto> reservationDtos) {
        List<Reservation> result = new ArrayList<>();

        reservationDtos.stream()
                .forEach(reservationDto -> {
                    result.add(mapToReservation(reservationDto));
                });


        return result;
    }

}
