package com.springboot.connectmate.repositories;

import com.springboot.connectmate.enums.*;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@TestPropertySource("classpath:application-db-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ThresholdBreachInsightRepositoryTests {

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
    private ThresholdBreachInsightRepository thresholdBreachInsightRepository;

    @Autowired
    private MetricRepository metricRepository;

    @DisplayName("JUnit Test for ThresholdBreachInsightRepository - Save Insight")
    @Test
    public void givenInsightObject_whenSave_thenReturnSavedInsight() {
        // given - precondition or setup
        Metric metric = Metric.builder()
                .code(ConnectMetricCode.SERVICE_LEVEL)
                .minimumThresholdValue(0.0)
                .maximumThresholdValue(100.0)
                .targetValue(50.0)
                .build();
        metricRepository.save(metric);

        ThresholdBreachInsight insight = ThresholdBreachInsight.builder()
                .connectItemId("connectItemId")
                .connectItemType(ConnectMetricType.INSTANCE)
                .value(100.0)
                .metricCode(metric)
                .occurredAt(LocalDateTime.now())
                .status(Status.TO_DO)
                .insightName("Insight Name")
                .insightSummary("Insight Summary")
                .insightDescription("Insight Description")
                .insightActions("Insight Actions")
                .insightCategory(InsightCategory.CRITICAL)
                .insightSeverity(InsightSeverity.HIGH)
                .insightRootCause("Insight Root Cause")
                .insightImpact("Insight Impact")
                .insightPrevention("Insight Prevention")
                .build();

        // when - action or operation
        ThresholdBreachInsight savedInsight = thresholdBreachInsightRepository.save(insight);

        // then - verification or assertion
        assertThat(savedInsight).isNotNull();
        assertThat(savedInsight.getId()).isNotNull();
        assertThat(savedInsight.getConnectItemId()).isEqualTo(insight.getConnectItemId());
        assertThat(savedInsight.getConnectItemType()).isEqualTo(insight.getConnectItemType());
        assertThat(savedInsight.getValue()).isEqualTo(insight.getValue());
    }

    @DisplayName("JUnit Test for ThresholdBreachInsightRepository - Find Insight by ID")
    @Test
    public void givenInsightId_whenFindById_thenReturnInsightObject() {
        // given - precondition or setup
        // given - precondition or setup
        Metric metric = Metric.builder()
                .code(ConnectMetricCode.SERVICE_LEVEL)
                .minimumThresholdValue(0.0)
                .maximumThresholdValue(100.0)
                .targetValue(50.0)
                .build();
        metricRepository.save(metric);

        ThresholdBreachInsight insight = ThresholdBreachInsight.builder()
                .connectItemId("connectItemId")
                .connectItemType(ConnectMetricType.INSTANCE)
                .value(100.0)
                .metricCode(metric)
                .occurredAt(LocalDateTime.now())
                .status(Status.TO_DO)
                .insightName("Insight Name")
                .insightSummary("Insight Summary")
                .insightDescription("Insight Description")
                .insightActions("Insight Actions")
                .insightCategory(InsightCategory.CRITICAL)
                .insightSeverity(InsightSeverity.HIGH)
                .insightRootCause("Insight Root Cause")
                .insightImpact("Insight Impact")
                .insightPrevention("Insight Prevention")
                .build();
        thresholdBreachInsightRepository.save(insight);

        // when - action or operation
        Optional<ThresholdBreachInsight> foundInsight = thresholdBreachInsightRepository.findById(insight.getId());

        // then - verification or assertion
        assertThat(foundInsight).isPresent();
        assertThat(foundInsight.get().getId()).isNotNull();
        assertThat(foundInsight.get().getConnectItemId()).isEqualTo(insight.getConnectItemId());
        assertThat(foundInsight.get().getConnectItemType()).isEqualTo(insight.getConnectItemType());
        assertThat(foundInsight.get().getValue()).isEqualTo(insight.getValue());
    }

    @DisplayName("JUnit Test for ThresholdBreachInsightRepository - Find All Insights")
    @Test
    public void whenFindAll_thenReturnInsightList() {
        // given - precondition or setup
        // given - precondition or setup
        Metric metric = Metric.builder()
                .code(ConnectMetricCode.SERVICE_LEVEL)
                .minimumThresholdValue(0.0)
                .maximumThresholdValue(100.0)
                .targetValue(50.0)
                .build();
        metricRepository.save(metric);

        ThresholdBreachInsight insight1 = ThresholdBreachInsight.builder()
                .connectItemId("connectItemId1")
                .connectItemType(ConnectMetricType.INSTANCE)
                .value(100.0)
                .metricCode(metric)
                .occurredAt(LocalDateTime.now())
                .status(Status.TO_DO)
                .insightName("Insight Name 1")
                .insightSummary("Insight Summary 1")
                .insightDescription("Insight Description 1")
                .insightActions("Insight Actions 1")
                .insightCategory(InsightCategory.CRITICAL)
                .insightSeverity(InsightSeverity.HIGH)
                .insightRootCause("Insight Root Cause 1")
                .insightImpact("Insight Impact 1")
                .insightPrevention("Insight Prevention 1")
                .build();
        ThresholdBreachInsight insight2 = ThresholdBreachInsight.builder()
                .connectItemId("connectItemId2")
                .connectItemType(ConnectMetricType.QUEUE)
                .value(200.0)
                .metricCode(metric)
                .occurredAt(LocalDateTime.now())
                .status(Status.TO_DO)
                .insightName("Insight Name 2")
                .insightSummary("Insight Summary 2")
                .insightDescription("Insight Description 2")
                .insightActions("Insight Actions 2")
                .insightCategory(InsightCategory.CRITICAL)
                .insightSeverity(InsightSeverity.HIGH)
                .insightRootCause("Insight Root Cause 2")
                .insightImpact("Insight Impact 2")
                .insightPrevention("Insight Prevention 2")
                .build();
        thresholdBreachInsightRepository.save(insight1);
        thresholdBreachInsightRepository.save(insight2);

        // when - action or operation
        Iterable<ThresholdBreachInsight> insightList = thresholdBreachInsightRepository.findAll();

        // then - verification or assertion
        assertThat(insightList).hasSize(2);
    }

    @DisplayName("JUnit Test for ThresholdBreachInsightRepository - Delete Insight")
    @Test
    public void givenInsightObject_whenDelete_thenRemoveInsight() {
        // given - precondition or setup
        // given - precondition or setup
        Metric metric = Metric.builder()
                .code(ConnectMetricCode.SERVICE_LEVEL)
                .minimumThresholdValue(0.0)
                .maximumThresholdValue(100.0)
                .targetValue(50.0)
                .build();
        metricRepository.save(metric);

        ThresholdBreachInsight insight = ThresholdBreachInsight.builder()
                .connectItemId("connectItemId")
                .connectItemType(ConnectMetricType.INSTANCE)
                .value(100.0)
                .metricCode(metric)
                .occurredAt(LocalDateTime.now())
                .status(Status.TO_DO)
                .insightName("Insight Name")
                .insightSummary("Insight Summary")
                .insightDescription("Insight Description")
                .insightActions("Insight Actions")
                .insightCategory(InsightCategory.CRITICAL)
                .insightSeverity(InsightSeverity.HIGH)
                .insightRootCause("Insight Root Cause")
                .insightImpact("Insight Impact")
                .insightPrevention("Insight Prevention")
                .build();
        thresholdBreachInsightRepository.save(insight);

        // when - action or operation
        thresholdBreachInsightRepository.delete(insight);
        Optional<ThresholdBreachInsight> deletedInsight = thresholdBreachInsightRepository.findById(insight.getId());

        // then - verification or assertion
        assertThat(deletedInsight).isEmpty();
    }
}
