package com.springboot.connectmate.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.connectmate.dtos.AmazonConnect.ConnectMetricFilterDTO;
import com.springboot.connectmate.enums.ConnectMetricType;
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
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource({
        "classpath:application-db-test.properties",
        "classpath:application-aws-test.properties"
})
public class ConnectMetricControllerTests {
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

    // QUEUE METRICS
    @Test
    public void givenInstanceArnAndQueueId_whenGetQueueMetrics_thenReturnQueueMetrics() throws Exception {
        // given - precondition or setup
        ConnectMetricFilterDTO filters = new ConnectMetricFilterDTO();
        filters.setInstanceArn("7c78bd60-4a9f-40e5-b461-b7a0dfaad848");
        filters.setQueueId("arn:aws:connect:us-east-1:674530197385:instance/7c78bd60-4a9f-40e5-b461-b7a0dfaad848/queue/f0813607-af92-4a36-91e6-630ababb643c");

        // when - action or behaviour tested
        ResultActions response = mockMvc.perform(get("/api/amazon-connect/instances/queue-metrics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filters)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // then - verify the result or output using assert statements
        response.andDo(print());
    }

    // AGENT METRICS
    @Test
    public void givenInstanceArnAndQueueId_whenGetAgentMetrics_thenReturnAgentMetrics() throws Exception {
        // given - precondition or setup
        String instanceId = "7c78bd60-4a9f-40e5-b461-b7a0dfaad848";
        String queueArn = "arn:aws:connect:us-east-1:674530197385:instance/7c78bd60-4a9f-40e5-b461-b7a0dfaad848/queue/f0813607-af92-4a36-91e6-630ababb643c";

        // when - action or behaviour tested
        ResultActions response = mockMvc.perform(get("/api/amazon-connect/instances/agent-metrics?instanceId=" + instanceId + "&queueArn=" + queueArn))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // then - verify the result or output using assert statements
        response.andDo(print());
    }
}
