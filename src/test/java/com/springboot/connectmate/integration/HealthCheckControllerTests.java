package com.springboot.connectmate.integration;

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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource({
        "classpath:application-db-test.properties",
        "classpath:application-aws-test.properties"
})
public class HealthCheckControllerTests {

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

    @Test
    public void givenStatusEndpoint_whenGetStatus_thenReturnServiceOperational() throws Exception {
        // when - action or behaviour tested
        ResultActions response = mockMvc.perform(
                        get("/api/health-check/status")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(content().string(containsString("Service is operational as of")));

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Service is operational as of")));
    }

    @Test
    public void givenVersionEndpoint_whenGetVersion_thenReturnCurrentVersion() throws Exception {
        // when - action or behaviour tested
        ResultActions response = mockMvc.perform(
                        get("/api/health-check/version")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(content().string(containsString("Current service version: 3.0.0, deployed on: 2024-06-03")));

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Current service version: 3.0.0, deployed on: 2024-06-03")));
    }

}
