package com.springboot.connectmate.models;

import com.springboot.connectmate.enums.MetricCategory;
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
@Table(name = "connect_metrics")
public class ConnectMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "metric_info_code")
    private MetricCategory metricInfoCode;

    @Column(name = "value", precision = 10, scale = 2)
    private BigDecimal value;

    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "queue_id")
    private Long queueId;

    @Column(name="date")
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "minimum_threshold", precision = 10, scale = 2)
    private BigDecimal minimumThreshold;

    @Column(name = "maximum_threshold", precision = 10, scale = 2)
    private BigDecimal maximumThreshold;

    @Column(name = "positive_upside")
    private boolean positiveUpside;
}
