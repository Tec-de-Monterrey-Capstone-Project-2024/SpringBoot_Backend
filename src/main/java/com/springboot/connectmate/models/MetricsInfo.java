package com.springboot.connectmate.models;

import com.springboot.connectmate.enums.Code;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods for us
@AllArgsConstructor // Lombok annotation to create a constructor with all the arguments
@NoArgsConstructor // Lombok annotation to create a constructor with no arguments

// MetricsInfo entity
@Entity
@Table(name = "metrics_info")
public class MetricsInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "code", nullable = false)
    private Code code;

    @Column(name = "is_positive", nullable = false)
    private Boolean isPositive;

    @Column(name = "is_general", nullable = false)
    private Boolean isGeneral;

    @Column(name = "threshold", nullable = false)
    private Long threshold;

    @Column(name = "name_template", nullable = false, columnDefinition = "TEXT")
    private String nameTemplate;

    @Column(name = "summary_template", nullable = false, columnDefinition = "TEXT")
    private String summaryTemplate;

    @Column(name = "situation_template", nullable = false, columnDefinition = "TEXT")
    private String situationTemplate;

    @Column(name = "actions_template", nullable = false, columnDefinition = "TEXT")
    private String actionsTemplate;
}