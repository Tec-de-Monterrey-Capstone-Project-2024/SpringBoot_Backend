package com.springboot.connectmate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springboot.connectmate.dtos.Metric.ThresholdBreachesRequestDTO;
import com.springboot.connectmate.enums.Performance;
import com.springboot.connectmate.enums.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration
public class MetricsInfoControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    @AutoConfigureTestDatabase
    public void addThresholdBreachForAgent() throws Exception {
        // Create user (firebase and connectmate, dummy data, no verification (no integrity))

        // Create metric

        // Create queue

        // Create & fill the request body (JSON)
        ThresholdBreachesRequestDTO requestBody = new ThresholdBreachesRequestDTO();
        requestBody.setMetricsInfoId(1L);
        requestBody.setAgentConnectId("ed7c391c-8e65-415f-950c-7347dcc9f067");
        requestBody.setQueueId("f0813607-af92-4a36-91e6-630ababb643c");
        requestBody.setValue(new BigDecimal(50));
        requestBody.setPerformance(Performance.BELOW_EXPECTATIONS);
        requestBody.setOccurredAt(LocalDateTime.now());
        requestBody.setStatus(Status.IN_PROGRESS);

        System.out.println(requestBody);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/metrics-info")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJSON(requestBody))
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private static String objectToJSON(Object object) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        return om.writeValueAsString(object);
    }

}
