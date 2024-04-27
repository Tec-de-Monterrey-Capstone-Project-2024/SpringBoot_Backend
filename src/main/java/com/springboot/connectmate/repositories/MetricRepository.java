package com.springboot.connectmate.repositories;

import com.springboot.connectmate.models.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricRepository extends JpaRepository<Metric, Long> {
}
