package com.robertreynisson.accountmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robertreynisson.accountmanager.controllers.AccountController;
import com.robertreynisson.accountmanager.controllers.domain.Role;
import com.robertreynisson.accountmanager.controllers.domain.UserAccountAccountCreate;
import com.robertreynisson.accountmanager.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Must have a running instance of the service available
 * <p>
 * Tests the basic functionality integration
 * create, then fetch, then update, then delete
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class ControllerCRUDTests {


    MockMvc mvc;
    UserAccountAccountCreate userAccountCreate;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    public AccountService accountService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        userAccountCreate = new UserAccountAccountCreate();
        userAccountCreate.setUserName("RobertReynisson");
        userAccountCreate.setFirstName("Robert");
        userAccountCreate.setLastName("Reynisson");
        userAccountCreate.setEmail("robert@robert.com");
        userAccountCreate.setPhone("+234234234234");
        userAccountCreate.setRole(Role.ADMIN);
        userAccountCreate.setPassword("Testing12345");

    }

    @Test
    @WithMockUser(username = "test", roles = {"ADMIN"})
    public void createUser() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writer().writeValueAsString(userAccountCreate);

        // Create
        mvc.perform(MockMvcRequestBuilders.post("/account/")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        // Fetch all
        MvcResult r = mvc.perform(MockMvcRequestBuilders.get("/account/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        r.getResponse().getContentAsString();

    }
}
