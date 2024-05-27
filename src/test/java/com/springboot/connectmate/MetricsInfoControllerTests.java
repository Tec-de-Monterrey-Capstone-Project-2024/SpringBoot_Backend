package com.springboot.connectmate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.services.impl.AmazonConnectServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

/*
@SpringBootTest
@AutoConfigureMockMvc
public class MetricsInfoControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private AmazonConnectServiceImpl amazonConnectServices = new AmazonConnectServiceImpl(new ModelMapper());

    private final static Random numberGenerator = new Random();


    private static Boolean generateRandomBoolean(){
        return numberGenerator.nextBoolean();
    }

    // Random data for metrics

    // Create dummy User Connect ID
    // Pattern 'XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX'
    private static String generateDummyAgentConnectId(){
        int randomNumber1Part1 = numberGenerator.nextInt(65536);
        int randomNumber1Part2 = numberGenerator.nextInt(65536);
        int randomNumber2 = numberGenerator.nextInt(65536);
        int randomNumber3 = numberGenerator.nextInt(65536);
        int randomNumber4 = numberGenerator.nextInt(65536);
        int randomNumber5Part1 = numberGenerator.nextInt(65536);
        int randomNumber5Part2 = numberGenerator.nextInt(65536);
        int randomNumber5Part3 = numberGenerator.nextInt(65536);

        return Integer.toHexString(randomNumber1Part1) +
                Integer.toHexString(randomNumber1Part2) + "-" +
                Integer.toHexString(randomNumber2) + "-" +
                Integer.toHexString(randomNumber3) + "-" +
                Integer.toHexString(randomNumber4) + "-" +
                Integer.toHexString(randomNumber5Part1) +
                Integer.toHexString(randomNumber5Part2) +
                Integer.toHexString(randomNumber5Part3);
    }

    /*
    @Test
    public void addValidThresholdBreachForAgent() throws Exception {
        // Create user (firebase and connectmate, dummy data, no verification (no integrity))
        Users u = new Users();
        u.setConnectId(generateDummyAgentConnectId());
        u.setFirebaseId(RandomStringUtils.randomAlphabetic(28));

        // Debug
        System.out.println("Amazon Connect ID: " + u.getConnectId());
        System.out.println("      Firebase ID: " + u.getFirebaseId());

        Users savedUser = usersRepository.save(u);

        // Create metric (dummy data)
        MetricsInfo m = new MetricsInfo();
        m.setCode(generateRandomMetricCategory());
        m.setIsPositive(generateRandomBoolean());
        m.setIsGeneral(generateRandomBoolean());
        m.setThreshold(numberGenerator.nextLong(200));
        m.setNameTemplate("'Name Template' attribute for Testing. The name of the metric.");
        m.setSummaryTemplate("'Summary Template' attribute for Testing");
        m.setSituationTemplate("'Situation Template' attribute for Testing");
        m.setActionsTemplate("''Actions Template' attribute for Testing");

        MetricsInfo savedMetric = metricsRepository.save(m);

        // Create queue
        // Queue q = client.createQueue();


        // Create & fill the request body (JSON)
        ThresholdBreachesRequestDTO requestBody = new ThresholdBreachesRequestDTO();
        requestBody.setMetricsInfoId(savedMetric.getId());
        requestBody.setAgentConnectId(savedUser.getConnectId());
        requestBody.setQueueId("f0813607-af92-4a36-91e6-630ababb643c");
        requestBody.setValue(new BigDecimal(50));
        requestBody.setPerformance(Performance.BELOW_EXPECTATIONS);
        requestBody.setOccurredAt(LocalDateTime.now());
        requestBody.setStatus(Status.IN_PROGRESS);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/metrics-info")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJSON(requestBody))
        ).andExpect(MockMvcResultMatchers.status().isCreated()
        ).andExpect(MockMvcResultMatchers.content().string(""));

        // Validate that the data was created in the database

        // Eliminate the new register

        // Eliminate relations of the new register

    }

     */
    /*
    private static String objectToJSON(Object object) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();

        // Add module to our ObjectMapper (DTO -> JSON) for support LocalDateTime
        om.registerModule(new JavaTimeModule());

        return om.writeValueAsString(object);
    }
}
*/
