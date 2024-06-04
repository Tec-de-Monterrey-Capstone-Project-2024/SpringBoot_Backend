package com.springboot.connectmate.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ConnectMetricControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // QUEUE METRICS
    @Test
    public void givenInstanceArnAndQueueId_whenGetQueueMetrics_thenReturnQueueMetrics() throws Exception {
        // given - precondition or setup
        String instanceArn = "arn:aws:connect:us-east-1:674530197385:instance/7c78bd60-4a9f-40e5-b461-b7a0dfaad848"; // connectmate
        String queueId = "f0813607-af92-4a36-91e6-630ababb643c"; // BasicQueue

        // when - action or behaviour tested
        ResultActions response = mockMvc.perform(get("/api/amazon-connect/instances/queue-metrics?instanceArn=" + instanceArn + "&queueId=" + queueId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // then - verify the result or output using assert statements
        response.andDo(print());
    }

    // AGENT METRICS
    @Test
    public void givenInstanceArnAndAgentId_whenGetAgentMetrics_thenReturnAgentMetrics() throws Exception {
        // given - precondition or setup
        String instanceArn = "arn:aws:connect:us-east-1:674530197385:instance/7c78bd60-4a9f-40e5-b461-b7a0dfaad848"; // connectmate
        String agentId = "ed7c391c-8e65-415f-950c-7347dcc9f067"; // antonio_connectmate

        // when - action or behaviour tested
        ResultActions response = mockMvc.perform(get("/api/amazon-connect/instances/agent-metrics?instanceArn=" + instanceArn + "&agentId=" + agentId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // then - verify the result or output using assert statements
        response.andDo(print());
    }
}
