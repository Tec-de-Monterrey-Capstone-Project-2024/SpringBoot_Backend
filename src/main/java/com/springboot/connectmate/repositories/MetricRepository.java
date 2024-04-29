package com.springboot.connectmate.repositories;

import com.springboot.connectmate.models.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface MetricRepository extends JpaRepository<Metric, Long> {
    @Procedure("sp_get_contact_center_metrics")
    List<Object[]> getContactCenterMetrics();
}
