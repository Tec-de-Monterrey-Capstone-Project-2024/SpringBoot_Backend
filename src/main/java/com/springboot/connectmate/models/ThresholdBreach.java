package com.springboot.connectmate.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.connectmate.enums.ThresholdBreachPerformanceCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods for us
@AllArgsConstructor // Lombok annotation to create a constructor with all the arguments
@NoArgsConstructor // Lombok annotation to create a constructor with no arguments

@Entity
@Table(
        name = "threshold_breaches",
        indexes = {
                @Index(columnList = "metric_id", name = "idx_threshold_breach_metric_id"),
        }
)
public class ThresholdBreach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to Metrics Table
    // Child side
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metric_id", nullable = false)
    @JsonIgnore
    private Metric metric;

    @Column(name = "value", precision = 10, scale = 2, nullable = false)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "breach_category", nullable = false,
            columnDefinition = "ENUM('CRITICAL', " +
                    "'UNSATISFACTORY', " +
                    "'BELOW_EXPECTATIONS', " +
                    "'EXCEEDS_EXPECTATIONS', " +
                    "'OUTSTANDING', " +
                    "'PIONEERING')"
            )
    private ThresholdBreachPerformanceCategory breachCategory;

    @Column(name = "occurred_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime occurredAt = LocalDateTime.now();

    // Link to Insights Table
    // Parent side
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "thresholdBreach", cascade = CascadeType.ALL, orphanRemoval = true)
    private Insight insight;

    

}
