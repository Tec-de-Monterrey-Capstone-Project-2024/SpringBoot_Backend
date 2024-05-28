package com.springboot.connectmate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ConnectUserControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void obtainAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/amazon-connect/instances/{instanceId}/users"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
