package com.robertreynisson.accountmanager.service;

import com.robertreynisson.accountmanager.controllers.domain.User;
import com.robertreynisson.accountmanager.controllers.domain.UserCreate;
import com.robertreynisson.accountmanager.data.UserRepo;
import com.robertreynisson.accountmanager.data.domain.UserDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final UserRepo userRepo;

    public AccountService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User createAccount(UserCreate userCreate) {
        UserDAO userDAO = new UserDAO(userCreate);
        userRepo.saveAndFlush(userDAO);

        return new User(userDAO);
    }

    public List<User> loadAllUsers() {
        return userRepo.findAll().stream().map(User::new).collect(Collectors.toList());
    }

}
