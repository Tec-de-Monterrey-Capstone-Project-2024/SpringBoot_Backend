package com.springboot.connectmate.model;

import java.time.LocalDateTime;

import com.springboot.connectmate.enums.ThresholdBreachPerformanceCategory;
import com.springboot.connectmate.models.Metric;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "AlertModel")
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metric_id") // Asume que hay una relación con una entidad Metric
    private Metric metric;

    private double value;

    @Enumerated(EnumType.STRING)
    private ThresholdBreachPerformanceCategory breachCategory;

    private LocalDateTime occurredAt;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public ThresholdBreachPerformanceCategory getBreachCategory() {
        return breachCategory;
    }

    public void setBreachCategory(ThresholdBreachPerformanceCategory breachCategory) {
        this.breachCategory = breachCategory;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(LocalDateTime occurredAt) {
        this.occurredAt = occurredAt;
    }
}
