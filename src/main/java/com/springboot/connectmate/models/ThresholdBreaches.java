package com.springboot.connectmate.models;

import com.springboot.connectmate.enums.Performance;
import com.springboot.connectmate.enums.Status;
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
@Table(name = "threshold_breaches")
public class ThresholdBreaches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "metrics_info_id", nullable = false)
    private MetricsInfo metricsInfoId;

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = true)
    private Users agentId;

    @Column(name = "queue_id", nullable = false, columnDefinition = "TEXT")
    private String queueId;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "performance", nullable = false)
    private Performance performance;

    @Column(name = "occurred_at", nullable = false)
    private LocalDateTime occurredAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
}
