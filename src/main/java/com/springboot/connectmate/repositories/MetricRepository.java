package com.springboot.connectmate.repositories;

import com.springboot.connectmate.models.Metric;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface MetricRepository extends JpaRepository<Metric, Long> {
    @Procedure("sp_get_contact_center_metrics")
    List<Object[]> getContactCenterMetrics();

    @Query(value = "SELECT * FROM v_get_agent_metrics WHERE agent_id = :agentId", nativeQuery = true)
    List<Tuple> getAgentMetrics(Long agentId);
}
