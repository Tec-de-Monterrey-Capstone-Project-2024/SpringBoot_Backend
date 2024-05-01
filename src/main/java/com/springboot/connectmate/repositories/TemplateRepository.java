package com.springboot.connectmate.repositories;

import com.springboot.connectmate.models.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    @Query(value = "SELECT t.* FROM threshold_breaches tb " +
            "INNER JOIN metrics m ON tb.metric_id = m.id " +
            "INNER JOIN templates t ON m.id = t.metric_id " +
            "WHERE tb.id = :breachId", nativeQuery = true)
    Template findTemplateByBreachIdSQL(@Param("breachId") Long breachId);
}
