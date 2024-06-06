package com.springboot.connectmate.integration;

import com.springboot.connectmate.enums.*;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.repositories.MetricRepository;
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
import com.springboot.connectmate.repositories.ThresholdBreachInsightRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    private ThresholdBreachInsightRepository thresholdBreachInsightRepository;


    @Autowired
    private MetricRepository metricRepository;

    private ThresholdBreachInsight existingInsight;

    @BeforeEach
    public void setUp() {
        thresholdBreachInsightRepository.deleteAll();
        metricRepository.deleteAll();

        Metric metric = new Metric();
        metric.setCode(ConnectMetricCode.SERVICE_LEVEL);
        metricRepository.save(metric);

        ThresholdBreachInsight insight = new ThresholdBreachInsight();
        insight.setMetricCode(metric);
        insight.setConnectItemId("sample-connect-item-id");
        insight.setConnectItemType(ConnectMetricType.INSTANCE);
        insight.setValue(99.9);
        insight.setOccurredAt(LocalDateTime.now());
        insight.setStatus(Status.TO_DO);
        insight.setInsightName("Sample Insight");
        insight.setInsightSummary("This is a summary.");
        insight.setInsightDescription("This is a description.");
        insight.setInsightActions("These are actions.");
        insight.setInsightCategory(InsightCategory.BELOW_EXPECTATIONS);
        insight.setInsightSeverity(InsightSeverity.HIGH);
        insight.setInsightRootCause("This is the root cause.");
        insight.setInsightImpact("This is the impact.");
        insight.setInsightPrevention("This is the prevention.");

        thresholdBreachInsightRepository.save(insight);
        existingInsight = thresholdBreachInsightRepository.save(insight);
    }

    @Test
    public void givenAlertsEndpoint_whenGetAlerts_thenReturnInsightAlertDTOList() throws Exception {
        // when - action or behavior that we are going to test
        ResultActions response = mockMvc.perform(
                get("/api/threshold-breach-insights/alerts")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].metricName").exists())
                .andExpect(jsonPath("$[0].insightCategory").exists())
                .andExpect(jsonPath("$[0].connectItemType").exists())
                .andExpect(jsonPath("$[0].occurredAt").exists());
    }

    @Test
    public void givenNoParams_whenGetInsights_thenReturnAllInsights() throws Exception {
        // when - action or behavior that we are going to test
        ResultActions response = mockMvc.perform(
                get("/api/threshold-breach-insights")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].insightName").exists())
                .andExpect(jsonPath("$[0].insightSummary").exists())
                .andExpect(jsonPath("$[0].insightSeverity").exists())
                .andExpect(jsonPath("$[0].status").exists());
    }

    @Test
    public void givenConnectItemId_whenGetInsights_thenReturnFilteredInsights() throws Exception {
        // given - test data configuration
        String connectItemId = "sample-connect-item-id";

        // when - action or behavior that we are going to test
        ResultActions response = mockMvc.perform(
                get("/api/threshold-breach-insights")
                        .param("connectItemId", connectItemId)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].insightName").exists())
                .andExpect(jsonPath("$[0].insightSummary").exists())
                .andExpect(jsonPath("$[0].insightSeverity").exists())
                .andExpect(jsonPath("$[0].status").exists());
    }

    @Test
    public void givenItemType_whenGetInsights_thenReturnFilteredInsights() throws Exception {
        // given - test data configuration
        ConnectMetricType itemType = ConnectMetricType.INSTANCE;

        // when - action or behavior that we are going to test
        ResultActions response = mockMvc.perform(
                get("/api/threshold-breach-insights")
                        .param("itemType", itemType.toString())
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].insightName").exists())
                .andExpect(jsonPath("$[0].insightSummary").exists())
                .andExpect(jsonPath("$[0].insightSeverity").exists())
                .andExpect(jsonPath("$[0].status").exists());
    }

    @Test
    public void givenInvalidParams_whenGetInsights_thenReturnBadRequest() throws Exception {
        // when - action or behavior that we are going to test
        ResultActions response = mockMvc.perform(
                get("/api/threshold-breach-insights")
                        .param("itemType", "INVALID_TYPE")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenInsightsByStatusEndpoint_whenGetInsightsByStatus_thenReturnInsightsGroupedByStatus() throws Exception {
        // when - action or behavior that we are going to test
        ResultActions response = mockMvc.perform(
                get("/api/threshold-breach-insights/by-status")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.TO_DO").isArray())
                .andExpect(jsonPath("$.TO_DO[0].id").exists())
                .andExpect(jsonPath("$.TO_DO[0].insightName").exists())
                .andExpect(jsonPath("$.TO_DO[0].insightSummary").exists())
                .andExpect(jsonPath("$.TO_DO[0].insightSeverity").exists())
                .andExpect(jsonPath("$.TO_DO[0].status").value("TO_DO"));
    }

    @Test
    public void givenMetricType_whenGetInsightsByMetricType_thenReturnInsightList() throws Exception {
        // Given - MetricType for the test
        ConnectMetricType metricType = ConnectMetricType.INSTANCE;

        // When - action or behaviour that we are going to test
        ResultActions response = mockMvc.perform(
                get("/api/threshold-breach-insights/status/" + metricType)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].insightName").exists())
                .andExpect(jsonPath("$[0].insightSummary").exists())
                .andExpect(jsonPath("$[0].insightSeverity").exists())
                .andExpect(jsonPath("$[0].status").exists());
    }

    @Test
    public void givenInsightId_whenGetInsightById_thenReturnInsightDetail() throws Exception {
        // Given - Fetch the ID of the insight we just saved in setUp
        ThresholdBreachInsight savedInsight = thresholdBreachInsightRepository.findAll().get(0);
        Long insightId = savedInsight.getId();

        // When - action or behaviour that we are going to test
        ResultActions response = mockMvc.perform(
                get("/api/threshold-breach-insights/{id}", insightId)  // URL must match exactly
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(insightId))
                .andExpect(jsonPath("$.connectItemId").exists())
                .andExpect(jsonPath("$.connectItemType").exists())
                .andExpect(jsonPath("$.value").exists())
                .andExpect(jsonPath("$.occurredAt").exists())
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.insightName").exists())
                .andExpect(jsonPath("$.insightSummary").exists())
                .andExpect(jsonPath("$.insightDescription").exists())
                .andExpect(jsonPath("$.insightActions").exists())
                .andExpect(jsonPath("$.insightCategory").exists())
                .andExpect(jsonPath("$.insightSeverity").exists())
                .andExpect(jsonPath("$.insightRootCause").exists())
                .andExpect(jsonPath("$.insightImpact").exists())
                .andExpect(jsonPath("$.insightPrevention").exists());
    }

    @Test
    public void givenValidIdAndStatus_whenUpdateInsightStatus_thenReturnUpdatedStatus() throws Exception {
        // Given - setup or precondition
        ThresholdBreachInsight savedInsight = thresholdBreachInsightRepository.findAll().get(0);
        Long insightId = savedInsight.getId();
        Status newStatus = Status.DONE;

        // When - action or behaviour that we are going to test
        ResultActions response = mockMvc.perform(
                patch("/api/threshold-breach-insights/{thresholdId}/status", insightId)
                        .param("newStatus", newStatus.name())
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Status updated successfully"));

        // Verify the status was updated in the database
        Optional<ThresholdBreachInsight> updatedInsight = thresholdBreachInsightRepository.findById(insightId);
        assertTrue(updatedInsight.isPresent());
        assertEquals(newStatus, updatedInsight.get().getStatus());
    }

    @Test
    public void givenInvalidStatus_whenUpdateInsightStatus_thenReturn400() throws Exception {
        // Given - precondition or setup
        Long id = existingInsight.getId();
        String invalidStatus = "INVALID_STATUS";

        // When - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(
                patch("/api/threshold-breach-insights/{thresholdId}/status", id)
                        .param("newStatus", invalidStatus)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Then - verify the output
        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type").value("about:blank"))
                .andExpect(jsonPath("$.title").value("Bad Request"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.detail").value("Failed to convert 'newStatus' with value: 'INVALID_STATUS'"))
                .andExpect(jsonPath("$.instance").value("/api/threshold-breach-insights/" + id + "/status"));
    }


    @Test
    public void givenInvalidId_whenUpdateInsightStatus_thenReturnNotFound() throws Exception {
        // Given - setup or precondition
        Long invalidId = Long.MAX_VALUE;
        Status newStatus = Status.DONE;

        // When - action or behaviour that we are going to test
        ResultActions response = mockMvc.perform(
                patch("/api/threshold-breach-insights/{thresholdId}/status", invalidId)
                        .param("newStatus", newStatus.name())
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isNotFound());
    }
}