package com.springboot.connectmate.repositories;

import com.springboot.connectmate.models.ThresholdBreach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThresholdBreachRepository extends JpaRepository<ThresholdBreach, Long> {
}
