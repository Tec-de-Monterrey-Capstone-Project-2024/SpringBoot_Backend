package com.springboot.connectmate.repositories;

import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.models.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends JpaRepository<Metric, ConnectMetricCode> {
    
}
