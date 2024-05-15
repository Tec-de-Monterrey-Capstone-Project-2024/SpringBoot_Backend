package com.springboot.connectmate.models;

import com.springboot.connectmate.enums.Code;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods for us
@AllArgsConstructor // Lombok annotation to create a constructor with all the arguments
@NoArgsConstructor // Lombok annotation to create a constructor with no arguments

@Entity
@Table(name = "metrics_info")
@IdClass(MetricsInfoId.class)
public class MetricsInfo {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "code")
    private Code code;

    @Id
    @Column(name = "is_positive")
    private Boolean isPositive;

    @Column(name = "threshold")
    private Long threshold;

    @Column(name = "name_template")
    private String nameTemplate;

    @Column(name = "summary_template")
    private String summaryTemplate;

    @Column(name = "situation_template")
    private String situationTemplate;

    @Column(name = "actions_template")
    private String actionsTemplate;

    @OneToMany(mappedBy = "code")
    private List<ThresholdBreaches> thresholdBreaches;

}