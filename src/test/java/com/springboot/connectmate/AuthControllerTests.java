package com.springboot.connectmate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springboot.connectmate.dtos.User.RegisterUserFormDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void createValidUser() throws Exception {
        RegisterUserFormDTO u = new RegisterUserFormDTO();
        u.setInstanceId("7c78bd60-4a9f-40e5-b461-b7a0dfaad848");
        u.setFirebaseId("jZ4E64wp9KTBISnb5QECKhccguN2");
        u.setEmail("a01026512@tec.mx");

        mockMvc.perform(MockMvcRequestBuilders.post("api/auth/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJSON(u)));
    }

    @Test
    public void createInvalidUser() throws Exception {

    }

    private static String objectToJSON(Object object) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();

        // Add module to our ObjectMapper (DTO -> JSON) for support LocalDateTime
        om.registerModule(new JavaTimeModule());

        return om.writeValueAsString(object);
    }
}
