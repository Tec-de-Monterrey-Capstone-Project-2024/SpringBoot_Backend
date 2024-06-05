package com.springboot.connectmate.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private ObjectMapper objectMapper;

    @Autowired
    private MetricRepository metricRepository;

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
    }

    @Test
    public void givenAlertsEndpoint_whenGetAlerts_thenReturnInsightAlertDTOList() throws Exception {
        // when - acción o comportamiento probado
        ResultActions response = mockMvc.perform(
                get("/api/threshold-breach-insights/alerts")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then - verifica el resultado o la salida utilizando declaraciones assert
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
}
