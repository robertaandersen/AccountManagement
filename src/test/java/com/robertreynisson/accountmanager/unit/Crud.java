package com.robertreynisson.accountmanager.unit;


import com.robertreynisson.accountmanager.controllers.domain.Role;
import com.robertreynisson.accountmanager.controllers.domain.User;
import com.robertreynisson.accountmanager.controllers.domain.UserCreate;
import com.robertreynisson.accountmanager.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Crud {

    private UserCreate userCreate;

    @Autowired
    public AccountService accountService;

    @Test
    public void createUser() {
        this.userCreate = new UserCreate();
        this.userCreate.setUserName("RobertReynisson");
        this.userCreate.setFirstName("Robert");
        this.userCreate.setLastName("Reynisson");
        this.userCreate.setEmail("robert@robert.com");
        this.userCreate.setPhone("+234234234234");
        this.userCreate.setRole(Role.ADMIN);
        this.userCreate.setPassword("Testing12345");
        accountService.createAccount(this.userCreate);
        List<User> users = accountService.loadAllUsers();

    }
}
