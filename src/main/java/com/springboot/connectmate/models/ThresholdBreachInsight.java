package com.springboot.connectmate.models;

import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.enums.InsightPerformance;
import com.springboot.connectmate.enums.InsightSeverity;
import com.springboot.connectmate.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder // Lombok annotation to instantiate the object with build function
@Getter
@Setter
@AllArgsConstructor // Lombok annotation to create a constructor with all the arguments
@NoArgsConstructor // Lombok annotation to create a constructor with no arguments
@Entity
@Table(
        name = "threshold_breach_insight",
        uniqueConstraints = {
                @UniqueConstraint( columnNames = { "metric_code", "connect_item_id" }, name = "uk_insight_metric_code_connect_item_id" )
        },
        indexes = {
                @Index(columnList = "metric_code, connect_item_id", name = "idx_insight_metric_code_connect_item_id"),
                @Index(columnList = "metric_code", name = "idx_insight_metric_code"),
                @Index(columnList = "connect_item_id", name = "idx_insight_connect_item_id"),
                @Index(columnList = "connect_item_type", name = "idx_insight_connect_item_type"),
                @Index(columnList = "status", name = "idx_insight_status"),
                @Index(columnList = "insight_category", name = "idx_insight_category"),
                @Index(columnList = "insight_severity", name = "idx_insight_severity"),
                @Index(columnList = "occurred_at", name = "idx_insight_occurred_at")
        }
)
public class ThresholdBreachInsight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "metric_code", nullable = false, foreignKey = @ForeignKey(name = "fk_insight_metric_code"))
    private Metric metricCode;

    @Column(name = "connect_item_id", nullable = false)
    private String connectItemId;

    @Enumerated(EnumType.STRING)
    @Column(name = "connect_item_type", nullable = false, columnDefinition = "ENUM('INSTANCE', 'QUEUE', 'AGENT')")
    private ConnectMetricType connectItemType;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "occurred_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime occurredAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('DONE', 'TO_DO', 'IN_PROGRESS') default 'TO_DO'")
    private Status status;

    @Column(name = "insight_name", nullable = false, columnDefinition = "TEXT")
    private String insightName;

    @Column(name = "insight_summary", nullable = false, columnDefinition = "TEXT")
    private String insightSummary;

    @Column(name = "insight_description", nullable = false, columnDefinition = "TEXT")
    private String insightDescription;

    @Column(name = "insight_actions", nullable = false, columnDefinition = "TEXT")
    private String insightActions;

    @Enumerated(EnumType.STRING)
    @Column(name = "insight_category", nullable = false, columnDefinition = "ENUM('CRITICAL', 'UNSATISFACTORY', 'BELOW_EXPECTATIONS', 'EXCEEDS_EXPECTATIONS', 'OUTSTANDING', 'PIONEERING', 'UNKNOWN')")
    private InsightPerformance insightCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "insight_severity", nullable = false, columnDefinition = "ENUM('LOW', 'MEDIUM', 'HIGH', 'CRITICAL', 'UNKNOWN')")
    private InsightSeverity insightSeverity;

    @Column(name = "insight_root_cause", nullable = false, columnDefinition = "TEXT")
    private String insightRootCause;

    @Column(name = "insight_impact", nullable = false, columnDefinition = "TEXT")
    private String insightImpact;

    @Column(name = "insight_prevention", nullable = false, columnDefinition = "TEXT")
    private String insightPrevention;

}
