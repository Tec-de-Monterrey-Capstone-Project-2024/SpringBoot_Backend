package com.springboot.connectmate.repositories;

import com.springboot.connectmate.models.Insight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface InsightRepository extends JpaRepository<Insight, Long> {
    Insight findByThresholdBreachId(Long thresholdBreachId);

    @Procedure(name = "sp_get_queue_insights")
    List<Object[]> getQueueInsights();

}
