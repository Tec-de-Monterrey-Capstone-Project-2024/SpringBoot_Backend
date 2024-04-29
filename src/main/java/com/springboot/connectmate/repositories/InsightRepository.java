package com.springboot.connectmate.repositories;

import com.springboot.connectmate.models.Insight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InsightRepository extends JpaRepository<Insight, Long> {
    Insight findByThresholdBreachId(Long thresholdBreachId);

    @Procedure(name = "GetQueueInsights")
    List<Object[]> getQueueInsights();

    @Query("SELECT i FROM Insight i " +
            "JOIN i.thresholdBreach tb " +
            "JOIN tb.metric m " +
            "JOIN m.user u " +
            "WHERE u.id = :agentId")
    List<Insight> findAllByAgentId(@Param("agentId") Long agentId);
}
