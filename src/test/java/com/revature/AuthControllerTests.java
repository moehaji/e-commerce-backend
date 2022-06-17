package com.revature;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.LoginRequest;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.services.AuthService;
import com.revature.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ECommerceApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class AuthControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository ur;


    private ObjectMapper om = new ObjectMapper();

    @BeforeEach
    public void resetDB() {
        ur.deleteAll();
    }

    @Test
    @Transactional
    @Nullable
    public void testLogin () throws Exception {
        User testUser = new User(0, "testUser@gmail.com", "password", "test", "user", false);
        User testUser2 = new User(0, "testUser1@gmail.com", "password", "test2", "user", false);
        ur.save(testUser);

        LoginRequest lr = new LoginRequest("testUser@gmail.com", "password");

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                                .content(om.writeValueAsString(lr))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("test"));
    }

}
