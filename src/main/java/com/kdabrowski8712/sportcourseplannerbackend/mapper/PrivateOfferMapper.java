package com.kdabrowski8712.sportcourseplannerbackend.mapper;

import com.kdabrowski8712.sportcourseplannerbackend.domain.*;
import com.kdabrowski8712.sportcourseplannerbackend.service.InstructorDBService;
import com.kdabrowski8712.sportcourseplannerbackend.service.ReservationDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PrivateOfferMapper {

    @Autowired
    private InstructorDBService instructorDBService;

    @Autowired
    private ReservationDBService reservationDBService;

    public PrivateOfferDto mapToPrivateOfferDto(PrivateOffer offer) {

        Address transferredAddress = new Address(offer.getAddress());

        PrivateOfferDto result = new PrivateOfferDto();

        result.setId(offer.getId());
        result.setAddress(transferredAddress);
        result.setName(offer.getName());
        result.setDescription(offer.getDescription());
        result.setCategory(offer.getCategory());
        result.setPrice(offer.getPrice());
        result.setDuration(offer.getDuration());

        result.setInstructor_id(offer.getInstructor().getId());

        if (offer.getReservations().size() != 0) {
            offer.getReservations().stream()
                    .forEach(reservation -> {
                        result.getReservationIds().add(reservation.getId());
                    });
        }

        return result;
    }

    public PrivateOffer mapToOffer(PrivateOfferDto privateOfferDto) {

        PrivateOffer result = new PrivateOffer();
        Address transferedAddress = new Address(privateOfferDto.getAddress());

        result.setName(privateOfferDto.getName());
        result.setId(privateOfferDto.getId());
        result.setAddress(transferedAddress);
        result.setDescription(privateOfferDto.getDescription());
        result.setCategory(privateOfferDto.getCategory());
        result.setPrice(privateOfferDto.getPrice());
        result.setDuration(privateOfferDto.getDuration());

        Optional<Instructor> potentialInstructor = instructorDBService.getInstructor(privateOfferDto.getInstructor_id());

        if (potentialInstructor.isPresent()) {
            result.setInstructor(potentialInstructor.get());
        }

        if (privateOfferDto.getReservationIds().size() != 0) {
            privateOfferDto.getReservationIds().stream()
                    .forEach(reservationId -> {

                        Optional<Reservation> potRes = reservationDBService.getReservation(reservationId);
                        if (potRes.isPresent()) {
                            result.getReservations().add(potRes.get());
                        }
                    });
        }

        return result;

    }

    public List<PrivateOfferDto> mapToPrivatOfferDtoList(final List<PrivateOffer> input) {

        List<PrivateOfferDto> privateOfferDtos = new ArrayList<>();

        input.stream()
                .forEach(privateOffer -> {
                    privateOfferDtos.add(mapToPrivateOfferDto(privateOffer));
                });

        return privateOfferDtos;
    }

    public List<PrivateOffer> mapToPrivateOfferList(final List<PrivateOfferDto> privateOfferDtos) {

        List<PrivateOffer> privateOffers = new ArrayList<>();

        privateOfferDtos.stream()
                .forEach(privateOfferDto -> {
                    privateOffers.add(mapToOffer(privateOfferDto));
                });

        return privateOffers;
    }
}
