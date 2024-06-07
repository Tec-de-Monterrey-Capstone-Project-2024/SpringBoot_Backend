package com.springboot.connectmate.repositories;

import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.models.Metric;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Testcontainers
@TestPropertySource("classpath:application-db-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MetricRepositoryTests {

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
    private MetricRepository metricRepository;

    @DisplayName("JUnit Test for MetricRepository - Update Metric")
    @Test
    public void givenMetricObject_whenUpdateMetric_thenReturnUpdatedMetric() {
        // given - precondition or setup
        Metric metric = Metric.builder()
                .code(ConnectMetricCode.SERVICE_LEVEL)
                .minimumThresholdValue(0.0)
                .maximumThresholdValue(100.0)
                .targetValue(50.0)
                .build();
        Metric savedMetric = metricRepository.save(metric);

        // when - action or operation
        Metric foundMetric = metricRepository.findById(savedMetric.getCode()).get();
        foundMetric.setTargetValue(75.0);
        Metric updatedMetric = metricRepository.save(foundMetric);

        // then - verification or assertion
        assertThat(updatedMetric.getTargetValue()).isEqualTo(75.0);
    }

}
