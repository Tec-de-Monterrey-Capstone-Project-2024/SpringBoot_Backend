package com.springboot.connectmate.repositories;

import com.springboot.connectmate.models.ThresholdBreachInsight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThresholdBreachInsightRepository extends JpaRepository<ThresholdBreachInsight, Long> {
}
