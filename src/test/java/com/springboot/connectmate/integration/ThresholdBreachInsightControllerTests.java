package com.springboot.connectmate.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightGenericDTO;
import com.springboot.connectmate.services.ThresholdBreachInsightService;
import com.springboot.connectmate.enums.InsightSeverity;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource("classpath:application-db-test.properties")
public class ThresholdBreachInsightControllerTests {

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

    @MockBean
    private ThresholdBreachInsightService thresholdBreachInsightService;


    @BeforeEach
    @AfterEach
    void setup(){
        userRepository.deleteAll();
    }

    @Test
    public void getInsightsByStatusTest() throws Exception {
        List<ThresholdBreachInsightGenericDTO> toDoInsights = new ArrayList<>();
        ThresholdBreachInsightGenericDTO queue1 = new ThresholdBreachInsightGenericDTO();
        queue1.setId(1L);
        queue1.setInsightName("Average answer speed");
        queue1.setInsightSummary("The average answer speed the threshold.");
        queue1.setInsightSeverity(InsightSeverity.LOW);
        queue1.setStatus(Status.TO_DO);
        toDoInsights.add(queue1);

        List<ThresholdBreachInsightGenericDTO> doneInsights = new ArrayList<>();
        ThresholdBreachInsightGenericDTO queue2 = new ThresholdBreachInsightGenericDTO();
        queue2.setId(2L);
        queue2.setInsightName("Reimbursement rate");
        queue2.setInsightSummary("The reimbursement rate is below the threshold.");
        queue2.setInsightSeverity(InsightSeverity.HIGH);
        queue2.setStatus(Status.DONE);
        doneInsights.add(queue2);

        Map<Status, List<ThresholdBreachInsightGenericDTO>> mockInsights = new HashMap<>();
        mockInsights.put(Status.TO_DO, toDoInsights);
        mockInsights.put(Status.DONE, doneInsights);
        when(thresholdBreachInsightService.getInsightsByStatus()).thenReturn(mockInsights);
        mockMvc.perform(get("/api/threshold-breach-insights/by-status")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getInsightsTest () throws Exception {
        List<ThresholdBreachInsightGenericDTO> mockInsights = new ArrayList<>();
        ThresholdBreachInsightGenericDTO insight1 = new ThresholdBreachInsightGenericDTO();
        insight1.setId(1L);
        insight1.setInsightName("Average answer speed");
        insight1.setInsightSummary("The average answer speed the threshold.");
        insight1.setInsightSeverity(InsightSeverity.LOW);
        insight1.setStatus(Status.TO_DO);
        mockInsights.add(insight1);
        when(thresholdBreachInsightService.getAllInsights()).thenReturn(mockInsights);
        mockMvc.perform(get("/api/threshold-breach-insights?connectItemId=1&itemType=QUEUE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


}
