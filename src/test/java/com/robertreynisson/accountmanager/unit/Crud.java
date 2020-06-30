package com.robertreynisson.accountmanager.unit;


import com.robertreynisson.accountmanager.controllers.domain.Role;
import com.robertreynisson.accountmanager.controllers.domain.UserAccountAccountCreate;
import com.robertreynisson.accountmanager.data.UserAccountRepo;
import com.robertreynisson.accountmanager.data.domain.UserAccountDAO;
import com.robertreynisson.accountmanager.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Crud {

    private UserAccountAccountCreate userAccountCreate;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AccountService accountService;

    @MockBean
    public UserAccountRepo userAccountRepo;


    @Captor
    ArgumentCaptor<UserAccountDAO> captor;


    @Before
    public void setup() {
        this.userAccountCreate = new UserAccountAccountCreate();
        this.userAccountCreate.setId(1l);
        this.userAccountCreate.setUserName("RobertReynisson");
        this.userAccountCreate.setFirstName("Robert");
        this.userAccountCreate.setLastName("Reynisson");
        this.userAccountCreate.setEmail("robert@robert.com");
        this.userAccountCreate.setPhone("+234234234234");
        this.userAccountCreate.setRole(Role.ADMIN);
        this.userAccountCreate.setPassword("Testing12345");
        Mockito.when(userAccountRepo.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(new UserAccountDAO(this.userAccountCreate)));
        Mockito.when(userAccountRepo.save(ArgumentMatchers.any(UserAccountDAO.class))).thenReturn(new UserAccountDAO(this.userAccountCreate));
    }

    @Test
    public void createUser() {
        accountService.createAccount(this.userAccountCreate);
        Mockito.verify(userAccountRepo, Mockito.times(1)).save(ArgumentMatchers.any(UserAccountDAO.class));
        Mockito.verify(userAccountRepo).save(captor.capture());
        Assertions.assertEquals("RobertReynisson", captor.getValue().getUserName());
        Assertions.assertEquals("Robert", captor.getValue().getFirstName());
        Assertions.assertEquals("Reynisson", captor.getValue().getLastName());
        Assertions.assertEquals("robert@robert.com", captor.getValue().getEmail());
        Assertions.assertEquals("+234234234234", captor.getValue().getPhone());
        Assertions.assertEquals("ADMIN", captor.getValue().getRole());
        Assertions.assertTrue(bCryptPasswordEncoder.matches("Testing12345", captor.getValue().getPassword()));
    }

    @Test
    public void update() {
        this.userAccountCreate.setUserName("xxx");


        accountService.updateUser(this.userAccountCreate);
        Mockito.verify(userAccountRepo, Mockito.times(1)).save(ArgumentMatchers.any(UserAccountDAO.class));
        Mockito.verify(userAccountRepo).save(captor.capture());
        Assertions.assertEquals("xxx", captor.getValue().getUserName());
        Assertions.assertEquals("Robert", captor.getValue().getFirstName());
        Assertions.assertEquals("Reynisson", captor.getValue().getLastName());
        Assertions.assertEquals("robert@robert.com", captor.getValue().getEmail());
        Assertions.assertEquals("+234234234234", captor.getValue().getPhone());
        Assertions.assertEquals("ADMIN", captor.getValue().getRole());
        Assertions.assertTrue(bCryptPasswordEncoder.matches("Testing12345", captor.getValue().getPassword()));
    }

}
