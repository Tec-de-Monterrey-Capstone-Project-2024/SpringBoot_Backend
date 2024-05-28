package com.springboot.connectmate.Integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.connectmate.dtos.User.RegisterUserFormDTO;
import com.springboot.connectmate.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenUserRegistrationForm_whenRegisterUser_thenReturnSaveUser() throws Exception {
        // given - precondition or setup
        RegisterUserFormDTO registerUserFormDTO = new RegisterUserFormDTO();
        registerUserFormDTO.setInstanceId("7c78bd60-4a9f-40e5-b461-b7a0dfaad848");
        registerUserFormDTO.setFirebaseId("BYFV06ZkYDTE4rWliVMgZWe2Egm1");
        registerUserFormDTO.setEmail("a01657142@tec.mx");

        // when - action or behaviour tested
        ResultActions response = mockMvc.perform(post("/api/auth/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerUserFormDTO))
        ).andExpect(status().isCreated()
        ).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email",
                        is(registerUserFormDTO.getEmail())))
                .andExpect(jsonPath("$.instanceId",
                        is(registerUserFormDTO.getInstanceId())));

        // Delete the user created
        userRepository.deleteById(registerUserFormDTO.getFirebaseId());
    }
}
