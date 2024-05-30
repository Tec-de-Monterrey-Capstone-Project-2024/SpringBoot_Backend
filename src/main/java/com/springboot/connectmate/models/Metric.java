package com.springboot.connectmate.models;

import com.springboot.connectmate.enums.ConnectMetricCode;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor // Lombok annotation to create a constructor with all the arguments
@NoArgsConstructor // Lombok annotation to create a constructor with no arguments
@Entity
@Table( name = "metric" )
public class Metric {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "code", nullable = false)
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