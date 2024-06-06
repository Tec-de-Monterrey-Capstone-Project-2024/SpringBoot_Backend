package com.springboot.connectmate.integration;

import com.amazonaws.services.connect.model.AgentStatusSummary;
import com.amazonaws.services.connect.model.RoutingProfileSummary;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.connectmate.models.User;
import com.springboot.connectmate.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource({
        "classpath:application-db-test.properties",
        "classpath:application-aws-test.properties"
})

public class ConnectUserControllerTest {

    @Container
    private static MySQLContainer mysqlContainer = new MySQLContainer("mysql:latest")
            .withDatabaseName("test_connectmate_db")
            .withUsername("test_user")
            .withPassword("test_password");
    static {
        mysqlContainer.start();
        System.setProperty("JDBC_URL", mysqlContainer.getJdbcUrl());
        System.setProperty("DB_NAME", "test_connectmate_db");
        System.setProperty("DB_USER", "test_user");
        System.setProperty("DB_PASSWORD", "test_password");
    }
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    @AfterEach
    void setup(){
        userRepository.deleteAll();
    }

    @Test
    public void givenInstanceIdAndUserId_whenGetUserDescription_thenReturnUser() throws Exception {
        // given - precondition or setup
        String instanceId = "7c78bd60-4a9f-40e5-b461-b7a0dfaad848";
        String id = "ed1ad50d-2ffc-44ad-a565-71f13ad991a5";
        String arn = "arn:aws:connect:us-east-1:674530197385:instance/7c78bd60-4a9f-40e5-b461-b7a0dfaad848/agent/ed1ad50d-2ffc-44ad-a565-71f13ad991a5";
        String username = "aram_connectmate";

        User expectedUser = new User();

        // when - action or behaviour tested
        ResultActions response = mockMvc.perform(get("/api/amazon-connect/instances/{instanceId}/users/{userId}/description", instanceId, id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedUser))
        ).andExpect(status().isOk()
        ).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.arn", is(arn)))
                .andExpect(jsonPath("$.username", is(username)))
                .andDo(print());
    }

    @Test
    public void givenInstanceId_whenGetRoutingProfile_thenReturnRoutingProfile() throws Exception {
        String instanceId = "7c78bd60-4a9f-40e5-b461-b7a0dfaad848";
        String id = "2cac12e4-47b9-4992-84e5-69cd31118a75";
        String arn ="arn:aws:connect:us-east-1:674530197385:instance/7c78bd60-4a9f-40e5-b461-b7a0dfaad848/routing-profile/2cac12e4-47b9-4992-84e5-69cd31118a75";
        String name = "Flashy Stardust";
        String lastModifiedTime = "2024-05-30T04:13:15.411+00:00";
        String lastModifiedRegion = "us-east-1";
        List<RoutingProfileSummary> expectedRoutingProfile = Collections.singletonList(new RoutingProfileSummary());

        ResultActions response = mockMvc.perform(get("/api/amazon-connect/instances/{instanceId}/routing-profiles", instanceId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedRoutingProfile))
        ).andExpect(status().isOk()
        ).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(id)))
                .andExpect(jsonPath("$[0].arn", is(arn)))
                .andExpect(jsonPath("$[0].name", is(name)))
                .andExpect(jsonPath("$[0].lastModifiedTime", is(lastModifiedTime)))
                .andExpect(jsonPath("$[0].lastModifiedRegion", is(lastModifiedRegion)))
                .andDo(print());

    }

    @Test
    public void givenInstanceId_whenGetAgentStatuses_thenReturnAgentStatuses() throws Exception {
        String instanceId = "7c78bd60-4a9f-40e5-b461-b7a0dfaad848";
        String id = "05619af9-666f-48c4-986a-4e8874942904";
        String arn ="arn:aws:connect:us-east-1:674530197385:instance/7c78bd60-4a9f-40e5-b461-b7a0dfaad848/agent-state/05619af9-666f-48c4-986a-4e8874942904";
        String name = "Available";
        String type = "ROUTABLE";
        String lastModifiedTime = "2024-05-15T20:38:52.933+00:00";
        String lastModifiedRegion = "us-east-1";
        List<AgentStatusSummary> expectedStatusSummary = Collections.singletonList(new AgentStatusSummary());

        ResultActions response = mockMvc.perform(get("/api/amazon-connect/instances/"+ instanceId + "/agent-statuses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedStatusSummary))
        ).andExpect(status().isOk()
        ).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(id)))
                .andExpect(jsonPath("$[0].arn", is(arn)))
                .andExpect(jsonPath("$[0].name", is(name)))
                .andExpect(jsonPath("$[0].type", is(type)))
                .andExpect(jsonPath("$[0].lastModifiedTime", is(lastModifiedTime)))
                .andExpect(jsonPath("$[0].lastModifiedRegion", is(lastModifiedRegion)))
                .andDo(print());
    }
}