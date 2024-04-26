package com.springboot.connectmate.models;

import com.springboot.connectmate.enums.MetricCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods for us
@AllArgsConstructor // Lombok annotation to create a constructor with all the arguments
@NoArgsConstructor // Lombok annotation to create a constructor with no arguments

@Entity
@Table(
        name = "metrics",
        uniqueConstraints = {
                @UniqueConstraint( columnNames = { "code", "user_id", "queue_id"})
        },
        indexes = {
                @Index(columnList = "code", name = "idx_metric_code"),
                @Index(columnList = "user_id", name = "idx_metric_user_id"),
                @Index(columnList = "queue_id", name = "idx_metric_queue_id")
        }
)
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to Users(Agents) Table
    // Child side
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Link to Queues Table
    // Child side
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_id", nullable = false)
    private Queue queue;

    @Enumerated(EnumType.STRING)
    @Column(name = "code", nullable = false)
    private MetricCategory code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "minimum_threshold", nullable = false, precision = 10, scale = 2)
    private BigDecimal minimumThreshold;

    @Column(name = "maximum_threshold", nullable = false, precision = 10, scale = 2)
    private BigDecimal maximumThreshold;

    @Column(name = "positive_upside", nullable = false)
    private boolean positiveUpside;

    // Link to ThresholdBreaches Table
    // Parent side
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "metric", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ThresholdBreach> thresholdBreaches;

    // Link to Templates Table
    // Parent side
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "metric", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Template> templates;
}

