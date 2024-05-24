package com.springboot.connectmate.repositories;

import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThresholdBreachInsightRepository extends JpaRepository<ThresholdBreachInsight, Long> {
    Optional<ThresholdBreachInsight> findByMetricCodeAndConnectItemId(Metric metricCode, String connectItemId);
}
