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
    @JoinColumn(name = "code", referencedColumnName = "code")
    private MetricsInfo code;

    @Column(name = "is_positive")
    private Boolean isPositive;

    @Column(name = "agent_id")
    private String agentId;

    @Column(name = "queue_id")
    private String queueId;

    @Column(name = "value")
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "performance")
    private Performance performance;

    @Column(name = "occurred_at")
    private LocalDateTime occurredAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}