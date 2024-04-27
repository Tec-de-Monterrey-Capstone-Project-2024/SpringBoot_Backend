package com.springboot.connectmate.enums;

import lombok.Getter;

@Getter
public enum MetricCategory {
    SL("service level"),
    FCR("first contact resolution"),
    ACR("abandonment call rate"),
    OCC("occupancy"),
    SA("schedule adherence"),
    ASA("average speed answer"),
    AHT("average handle time"),
    VFR("virtual floor reconfiguration");

    private final String description;

    MetricCategory(String description) {
        this.description = description;
    }
}
