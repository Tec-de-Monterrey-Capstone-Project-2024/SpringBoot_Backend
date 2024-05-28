package com.springboot.connectmate.models;

import com.springboot.connectmate.enums.ConnectMetricCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "metric")
public class Metric {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "code", nullable = false, length = 50) // Ajusta la longitud según sea necesario
    private ConnectMetricCode code;

    @Column(name = "minimum_threshold_value", nullable = true)
    private Double minimumThresholdValue;

    @Column(name = "maximum_threshold_value", nullable = true)
    private Double maximumThresholdValue;

    @Column(name = "target_value", nullable = true)
    private Double targetValue;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "metricCode", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ThresholdBreachInsight> thresholdBreachInsights;
}
