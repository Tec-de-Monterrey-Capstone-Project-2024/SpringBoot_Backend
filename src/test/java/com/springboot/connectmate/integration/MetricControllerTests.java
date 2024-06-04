package com.springboot.connectmate.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.connectmate.dtos.Metric.MetricDTO;
import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.services.MetricService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource("classpath:application-db-test.properties")
public class MetricControllerTests {

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
    private ObjectMapper objectMapper;

    @MockBean
    private MetricService metricService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testGetAllMetrics() throws Exception {
        List<MetricDTO> mockMetrics = new ArrayList<>();
        MetricDTO metric1 = new MetricDTO(ConnectMetricCode.SERVICE_LEVEL, 0.0, 100.0, 80.0);
        MetricDTO metric2 = new MetricDTO(ConnectMetricCode.ABANDONMENT_RATE, 0.0, 100.0, 5.0);

        mockMetrics.add(metric1);
        mockMetrics.add(metric2);

        when(metricService.getAllConnectMateMetrics()).thenReturn(mockMetrics);

        mockMvc.perform(get("/api/metrics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(mockMetrics.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value(metric1.getCode().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].minimumThresholdValue").value(metric1.getMinimumThresholdValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].maximumThresholdValue").value(metric1.getMaximumThresholdValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].targetValue").value(metric1.getTargetValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].code").value(metric2.getCode().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].minimumThresholdValue").value(metric2.getMinimumThresholdValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].maximumThresholdValue").value(metric2.getMaximumThresholdValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].targetValue").value(metric2.getTargetValue()));
    }
}
