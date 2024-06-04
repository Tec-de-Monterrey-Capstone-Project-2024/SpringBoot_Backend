package com.springboot.connectmate.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.InsightAlertDTO;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightDetailDTO;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightGenericDTO;
import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.enums.InsightCategory;
import com.springboot.connectmate.enums.InsightSeverity;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.repositories.UserRepository;
import com.springboot.connectmate.services.ThresholdBreachInsightService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
        System.setProperty("DB_PASSWORD", mysqlContainer.getPassword());
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
    void setup() {
        userRepository.deleteAll();
    }

    @Test
    void updateInsightStatusTest() throws Exception {

        Long thresholdId = 1L;
        Status newStatus = Status.IN_PROGRESS;

        mockMvc.perform(patch("/api/threshold-breach-insights/{thresholdId}/status", thresholdId)
                        .param("newStatus", newStatus.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testGetAlerts() throws Exception {
        // Mocking the service response
        List<InsightAlertDTO> mockAlerts = new ArrayList<>();
        InsightAlertDTO alert1 = new InsightAlertDTO();
        alert1.setId(1L);
        alert1.setMetricCode(ConnectMetricCode.SERVICE_LEVEL);
        alert1.setInsightCategory(InsightCategory.CRITICAL);  // Cambio de InsightPerformance a InsightCategory
        alert1.setConnectItemType(ConnectMetricType.AGENT);
        alert1.setOccurredAt("Fri May 03 17:29:27 CST 2024");

        InsightAlertDTO alert2 = new InsightAlertDTO();
        alert2.setId(2L);
        alert2.setMetricCode(ConnectMetricCode.ABANDONMENT_RATE);
        alert2.setInsightCategory(InsightCategory.UNSATISFACTORY);  // Cambio de InsightPerformance a InsightCategory
        alert2.setConnectItemType(ConnectMetricType.QUEUE);
        alert2.setOccurredAt("Sat May 04 10:15:30 CST 2024");

        mockAlerts.add(alert1);
        mockAlerts.add(alert2);

        when(thresholdBreachInsightService.getAlerts()).thenReturn(mockAlerts);

        mockMvc.perform(get("/api/threshold-breach-insights/alerts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(mockAlerts.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(alert1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].metricCode").value(alert1.getMetricCode().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].insightCategory").value(alert1.getInsightCategory().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].connectItemType").value(alert1.getConnectItemType().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].occurredAt").value(alert1.getOccurredAt()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(alert2.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].metricCode").value(alert2.getMetricCode().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].insightCategory").value(alert2.getInsightCategory().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].connectItemType").value(alert2.getConnectItemType().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].occurredAt").value(alert2.getOccurredAt()));
    }

    @Test
    public void GetInsightById() throws Exception {
        Long insightId = 1L;
        ThresholdBreachInsightDetailDTO mockInsight = new ThresholdBreachInsightDetailDTO();
        mockInsight.setId(insightId);

        when(thresholdBreachInsightService.getInsightById(insightId)).thenReturn(mockInsight);

        mockMvc.perform(get("/api/threshold-breach-insights/" + insightId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(insightId));
    }

    @Test
    public void getQueuesByStatusTest () throws Exception{
        List<ThresholdBreachInsightGenericDTO> mockInsights = new ArrayList<>();
        ThresholdBreachInsightGenericDTO queue1 = new ThresholdBreachInsightGenericDTO();
        queue1.setId(1L);
        queue1.setInsightName("Average answer speed");
        queue1.setInsightSummary("The average answer speed the threshold.");
        queue1.setInsightSeverity(InsightSeverity.LOW);
        queue1.setStatus(Status.TO_DO);

        ThresholdBreachInsightGenericDTO queue2 = new ThresholdBreachInsightGenericDTO();
        queue2.setId(2L);
        queue2.setInsightName("Reimbursement rate");
        queue2.setInsightSummary("The reimbursement rate is below the threshold.");
        queue2.setInsightSeverity(InsightSeverity.HIGH);
        queue2.setStatus(Status.DONE);


        mockInsights.add(queue1);
        mockInsights.add(queue2);

        when(thresholdBreachInsightService.getInsightsByStatus()).thenReturn((Map<Status, List<ThresholdBreachInsightGenericDTO>>) mockInsights);

        mockMvc.perform(get("/api/threshold-breach-insights/by-status")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(mockInsights.size()))
                .andExpect(jsonPath("$[0].id").value(queue1.getId()))
                .andExpect(jsonPath("$[0].insightName").value(queue1.getInsightName()))
                .andExpect(jsonPath("$[0].insightSummary").value(queue1.getInsightSummary()))
                .andExpect(jsonPath("$[0].insightSeverity").value(queue1.getInsightSeverity().name()))
                .andExpect(jsonPath("$[0].status").value(queue1.getStatus().name()))
                .andExpect(jsonPath("$[1].id").value(queue2.getId()))
                .andExpect(jsonPath("$[1].insightName").value(queue2.getInsightName()))
                .andExpect(jsonPath("$[1].insightSummary").value(queue2.getInsightSummary()))
                .andExpect(jsonPath("$[1].insightSeverity").value(queue2.getInsightSeverity().name()))
                .andExpect(jsonPath("$[1].status").value(queue2.getStatus().name()));
    }

}
