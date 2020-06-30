package com.robertreynisson.accountmanager.controllers;

import com.robertreynisson.accountmanager.controllers.domain.User;
import com.robertreynisson.accountmanager.controllers.domain.UserCreate;
import com.robertreynisson.accountmanager.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {


    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public List<User> GetAll() {
        return accountService.loadAllUsers();
    }

    @GetMapping("/{id}")
    public String GetById(@PathVariable(value = "id") Long id) {
        return "test " + id;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public User Post(@RequestBody UserCreate userCreate) {
        return accountService.createAccount(userCreate);
    }

    @PutMapping("/")
    public String Put() {
        return "post test";
    }

    @DeleteMapping("/{id}")
    public String Delete(@PathVariable(value = "id") Long id) {
        return "post test";
    }

}
