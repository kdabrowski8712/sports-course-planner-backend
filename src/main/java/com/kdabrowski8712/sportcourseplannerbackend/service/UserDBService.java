package com.kdabrowski8712.sportcourseplannerbackend.service;

import com.kdabrowski8712.sportcourseplannerbackend.domain.User;
import com.kdabrowski8712.sportcourseplannerbackend.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDBService {

    @Autowired
    private UserDao userDao;

    public User saveUser(User user) {
        return userDao.save(user);
    }

    public Optional<User> getUser(Long id) {
        return userDao.findById(id);
    }

    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }
}
