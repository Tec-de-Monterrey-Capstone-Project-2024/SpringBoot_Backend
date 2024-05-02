package com.springboot.connectmate.repositories;

import com.springboot.connectmate.models.Metric;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.math.BigDecimal;
import java.util.List;

public interface MetricRepository extends JpaRepository<Metric, Long> {
    @Procedure("sp_get_contact_center_metrics")
    List<Object[]> getContactCenterMetrics();
    

    @Query(value = "SELECT * FROM v_get_agent_metrics WHERE agent_id = :agentId", nativeQuery = true)
    List<Tuple> getAgentMetrics(Long agentId);

    @Query("UPDATE Metric m SET m.minimumThreshold = 0, m.maximumThreshold = 9999999 WHERE m.id = :metricId")
    void removeThresholds(Long metricId);

    @Query("UPDATE Metric m SET m.minimumThreshold = :minimumThreshold, m.maximumThreshold = :maximumThreshold WHERE m.id = :metricId")
    void updateThresholds(Long metricId, BigDecimal minimumThreshold, BigDecimal maximumThreshold);
}
