package com.springboot.connectmate.repositories;

import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThresholdBreachInsightRepository extends JpaRepository<ThresholdBreachInsight, Long> {
    Optional<ThresholdBreachInsight> findByMetricCodeAndConnectItemId(Metric metricCode, String connectItemId);
    @Modifying
    @Query("delete from ThresholdBreachInsight t where t.id = :id")
    void deleteById(Long id);
    List<ThresholdBreachInsight> findByStatus(Status status);
    List<ThresholdBreachInsight> findByConnectItemId(String connectItemId);
    List<ThresholdBreachInsight> findByConnectItemType(ConnectMetricType connectItemType);
}
