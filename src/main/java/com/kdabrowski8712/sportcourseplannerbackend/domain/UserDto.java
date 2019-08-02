package com.kdabrowski8712.sportcourseplannerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto extends GenericUserDto {
    private Long id;
    private List<Long> reservationIds = new ArrayList<>();

    public UserDto(String name, String surname, String description, Address address, Long id) {
        super(name, surname, description, address);
        this.id = id;
    }
}
