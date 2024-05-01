package com.springboot.connectmate.repositories;

import com.springboot.connectmate.models.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import com.springboot.connectmate.enums.MetricCategory;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MetricRepository extends JpaRepository<Metric, Long> {
    @Procedure("sp_get_contact_center_metrics")
    List<Object[]> getContactCenterMetrics();
    
    @Transactional
    @Modifying
    @Query("UPDATE Metric m SET m.minimumThreshold = NULL, m.maximumThreshold = NULL WHERE m.user.id = :userId AND m.queue.id = :queueId AND m.code = :code")
    void removeThresholds(Long userId, Long queueId, MetricCategory code);
}
