package com.springboot.connectmate.integration;

import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.repositories.MetricRepository;
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

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource({
        "classpath:application-db-test.properties",
        "classpath:application-aws-test.properties"
})
public class MetricControllerTests {

    @Container
    private static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:latest")
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
    private MetricRepository metricRepository;

    @BeforeEach
    @AfterEach
    void setup(){
        metricRepository.deleteAll();
    }

    @Test
    public void givenMetricCode_whenGetMetric_thenReturnMetric() throws Exception {
        // given - precondition or setup
        ConnectMetricCode code = ConnectMetricCode.SERVICE_LEVEL;
        Metric metric = new Metric();
        metric.setCode(code);
        metricRepository.save(metric);

        // when - action or behaviour tested
        ResultActions response = mockMvc.perform(get("/api/metrics/{code}", code)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(code.toString())));
    }

    @Test
    public void givenThresholdsAndTarget_whenSetThresholdsAndTarget_thenReturnUpdatedMetric() throws Exception {
        // given - precondition or setup
        ConnectMetricCode code = ConnectMetricCode.SERVICE_LEVEL;
        Double minThreshold = 10.0;
        Double maxThreshold = 100.0;
        Double targetValue = 50.0;
        Metric metric = new Metric();
        metric.setCode(code);
        metricRepository.save(metric);

        // when - action or behaviour tested
        ResultActions response = mockMvc.perform(post("/api/metrics/{code}/setThresholdsAndTarget", code)
                        .param("minThreshold", minThreshold.toString())
                        .param("maxThreshold", maxThreshold.toString())
                        .param("targetValue", targetValue.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(code.toString())))
                .andExpect(jsonPath("$.minimumThresholdValue", is(minThreshold)))
                .andExpect(jsonPath("$.maximumThresholdValue", is(maxThreshold)))
                .andExpect(jsonPath("$.targetValue", is(targetValue)));
    }
}

