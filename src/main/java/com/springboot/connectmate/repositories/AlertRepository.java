package com.springboot.connectmate.repositories;

import com.springboot.connectmate.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    @Procedure(name = "sp_get_alerts_by_type")
    List<Alert> getAlertsByType(@Param("type") String type);

    @Procedure(name = "sp_process_alerts")
    void processAlerts();
}
