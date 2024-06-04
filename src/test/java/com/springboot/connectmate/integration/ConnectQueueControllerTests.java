package com.springboot.connectmate.integration;

import com.amazonaws.services.connect.model.Queue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.connectmate.dtos.AmazonConnect.ConnectCurrentUserDataDTO;
import com.springboot.connectmate.dtos.AmazonConnect.ConnectQueueDTO;
import com.springboot.connectmate.dtos.AmazonConnect.ConnectQueueInfoDTO;
import com.springboot.connectmate.services.AmazonConnectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource("classpath:application-aws-test.properties")
public class ConnectQueueControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AmazonConnectService amazonConnectService;

    @Test
    public void givenInstanceId_whenListQueues_thenReturnAllQueues() throws Exception {
        // given - precondition or setup
        String instanceId = "7c78bd60-4a9f-40e5-b461-b7a0dfaad848";
        List<ConnectQueueDTO> expectedQueues = Collections.singletonList(new ConnectQueueDTO());

        // when - action or behaviour tested
        ResultActions response = mockMvc.perform(get("/api/amazon-connect/instances/{instanceId}/queues", instanceId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedQueues))
        ).andExpect(status().isOk()
        ).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenInstanceIdAndQueueId_whenDescribeQueue_thenReturnAllQueueInfo() throws Exception {
        // given - precondition or setup
        String instanceId = "7c78bd60-4a9f-40e5-b461-b7a0dfaad848";
        String queueId = "f0813607-af92-4a36-91e6-630ababb643c";
        Queue expectedQueue = new Queue();

        // when - action or behaviour tested
        ResultActions response = mockMvc.perform(get("/api/amazon-connect/instances/{instanceId}/queues/{queueId}/description", instanceId, queueId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedQueue))
        ).andExpect(status().isOk()
        ).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenInstanceId_whenGetCurrentUserData_thenReturnCurrentUserData() throws Exception {
        // given - precondition or setup
        String instanceId = "7c78bd60-4a9f-40e5-b461-b7a0dfaad848";
        List<ConnectCurrentUserDataDTO> expectedCurrentUserData = Collections.singletonList(new ConnectCurrentUserDataDTO());

        // when - action or behaviour tested
        ResultActions response = mockMvc.perform(get("/api/amazon-connect/instances/{instanceId}/current-user-data", instanceId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedCurrentUserData))
        ).andExpect(status().isOk()
        ).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenInstanceId_whenGetQueueUserCounts_thenReturnUserCounts() throws Exception {
        // given - precondition or setup
        String instanceId = "7c78bd60-4a9f-40e5-b461-b7a0dfaad848";

        // when - action or behaviour tested
        ResultActions response = mockMvc.perform(get("/api/amazon-connect/instances/{instanceId}/queue-users", instanceId)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()
        ).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk());
    }
}
