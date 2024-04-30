package com.springboot.connectmate.models;

import com.springboot.connectmate.enums.InsightStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods for us
@AllArgsConstructor // Lombok annotation to create a constructor with all the arguments
@NoArgsConstructor // Lombok annotation to create a constructor with no arguments

@Entity
@Table(
        name = "insights",
        indexes = {
                @Index(columnList = "status", name = "idx_insight_status")
        }
)
@NamedStoredProcedureQuery(
        name = "sp_get_queue_insights",
        procedureName = "sp_get_queue_insights",
        parameters = {
        }
)
public class Insight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to ThresholdBreach Table
    // Child side
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "threshold_breach_id", nullable = false, unique = true)
    private ThresholdBreach thresholdBreach;

    @Column(name = "constructed_description", nullable = false, columnDefinition = "TEXT")
    private String constructedDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('DONE', 'TO_DO', 'IN_PROGRESS') default 'TO_DO'")
    private InsightStatus status;

}
