package com.springboot.connectmate.repositories;

import com.springboot.connectmate.models.Insight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsightRepository extends JpaRepository<Insight, Long> {
}
