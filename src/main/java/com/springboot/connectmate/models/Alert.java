package com.springboot.connectmate.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.springboot.connectmate.enums.ThresholdBreachPerformanceCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "metric_id")
    private Long metricID;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "severity")
    private String severity;

    @Column(name = "min_threshold")
    private Long minThreshold;

    @Column(name = "max_threshold")
    private Long maxThreshold;

    @Column(name = "supervisor")
    private String supervisor;

    @Column(name = "agent")
    private String agent;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public double getValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValue'");
    }

    public ThresholdBreachPerformanceCategory getBreachCategory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBreachCategory'");
    }

    public LocalDateTime getOccurredAt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOccurredAt'");
    }

    public Object getMetric() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMetric'");
    }
}