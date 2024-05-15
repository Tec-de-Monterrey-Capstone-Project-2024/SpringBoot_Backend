package com.springboot.connectmate.repositories;

import com.springboot.connectmate.models.MetricsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricsInfoRepository extends JpaRepository<MetricsInfo, Long> {
}
