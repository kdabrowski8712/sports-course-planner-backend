package com.kdabrowski8712.sportcourseplannerbackend.mapper;

import com.kdabrowski8712.sportcourseplannerbackend.domain.Address;
import com.kdabrowski8712.sportcourseplannerbackend.domain.Reservation;
import com.kdabrowski8712.sportcourseplannerbackend.domain.User;
import com.kdabrowski8712.sportcourseplannerbackend.domain.UserDto;
import com.kdabrowski8712.sportcourseplannerbackend.service.ReservationDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserMapper {

    @Autowired
    private ReservationDBService reservationDBService;

    public UserDto mapToUserDto(User user) {

        Address copied = new Address(user.getAddress());
        UserDto userDto = new UserDto(user.getName(),user.getSurname(),user.getDescription(),
                copied,user.getId());

        user.getReservations().stream()
                .forEach(reservation -> {
                    userDto.getReservationIds().add(reservation.getId());
                });


        return userDto;
    }

    public User mapToUser(UserDto userDto) {

        Address copied = new Address(userDto.getAddress());

        User user = new User(userDto.getName(),userDto.getSurname(),userDto.getDescription(),copied);
        user.setId(userDto.getId());

        userDto.getReservationIds().stream()
                .forEach(reservationId -> {
                    Optional<Reservation> res = reservationDBService.getReservation(reservationId);
                    user.getReservations().add(res.get());
                });

        return user;
    }

    public List<UserDto> maptoUserDtoList(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();

        users.stream()
                .forEach(user -> {
                    userDtos.add(mapToUserDto(user));
                });

        return userDtos;
    }

    public List<User> mapToUserList(List<UserDto> userDtoList) {
        List<User> users = new ArrayList<>();

        userDtoList.stream()
                .forEach(userDto -> {
                    users.add(mapToUser(userDto));
                });

        return users;
    }
}
