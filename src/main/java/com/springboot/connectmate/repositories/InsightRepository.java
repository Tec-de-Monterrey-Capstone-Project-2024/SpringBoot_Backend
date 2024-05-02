package com.springboot.connectmate.repositories;

import com.springboot.connectmate.enums.InsightStatus;
import com.springboot.connectmate.models.Insight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface InsightRepository extends JpaRepository<Insight, Long> {
    Insight findByThresholdBreachId(Long thresholdBreachId);
    @Procedure(name = "sp_get_queue_insights")
    List<Object[]> getQueueInsights();
    @Query(value = "SELECT i.* FROM insights i " +
            "INNER JOIN threshold_breaches tb ON i.threshold_breach_id = tb.id " +
            "INNER JOIN metrics m ON tb.metric_id = m.id " +
            "INNER JOIN users u ON m.user_id = u.id " +
            "WHERE u.id = :agentId", nativeQuery = true)
    List<Insight> findAllByAgentId(@Param("agentId") Long agentId);
    List<Insight> findByStatus(InsightStatus status);
}
