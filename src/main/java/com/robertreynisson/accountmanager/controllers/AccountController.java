package com.robertreynisson.accountmanager.controllers;

import com.robertreynisson.accountmanager.controllers.domain.UserAccount;
import com.robertreynisson.accountmanager.controllers.domain.UserAccountAccountCreate;
import com.robertreynisson.accountmanager.service.AccountService;
import com.robertreynisson.accountmanager.service.domain.UserAccountException;
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
    public List<UserAccount> GetAll() throws UserAccountException.APIError {
        return accountService.loadAllUsers();
    }

    @GetMapping("/{id}")
    public UserAccount GetById(@PathVariable(value = "id") Long id) throws UserAccountException.APIError {
        return accountService.loadUserById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public UserAccount Post(@RequestBody UserAccountAccountCreate userAccountCreate) throws UserAccountException.APIError {
        return accountService.createAccount(userAccountCreate);
    }

    @PutMapping("/")
    public UserAccount Put(@RequestBody UserAccountAccountCreate userAccountAccountCreate) throws UserAccountException.APIError {
        return accountService.updateUser(userAccountAccountCreate);
    }

    @DeleteMapping("/{id}")
    public void Delete(@PathVariable(value = "id") Long id) throws UserAccountException.APIError {
        accountService.deleteUser(id);
    }

}
