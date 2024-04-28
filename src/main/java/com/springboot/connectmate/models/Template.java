package com.springboot.connectmate.models;

import com.springboot.connectmate.enums.TemplateType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods for us
@AllArgsConstructor // Lombok annotation to create a constructor with all the arguments
@NoArgsConstructor // Lombok annotation to create a constructor with no arguments

@Entity
@Table(
        name = "templates",
        indexes = {
                @Index(columnList = "metric_id", name = "idx_template_metric_id"),
        }
)
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to Metrics Table
    // Child side
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metric_id", nullable = false)
    private Metric metric;

    @Column(name = "name_template", nullable = false, columnDefinition = "TEXT")
    private String nameTemplate;

    @Column(name = "summary_template", nullable = false, columnDefinition = "TEXT")
    private String summaryTemplate;

    @Column(name = "situation_template", nullable = false, columnDefinition = "TEXT")
    private String situationTemplate;

    @Column(name = "actions_template", nullable = false, columnDefinition = "TEXT")
    private String actionsTemplate;

    @Enumerated(EnumType.STRING)
    @Column(name = "template_type", nullable = false,  columnDefinition = "ENUM('POSITIVE_UPSIDE', 'NEGATIVE_UPSIDE')")
    private TemplateType templateType;
}
