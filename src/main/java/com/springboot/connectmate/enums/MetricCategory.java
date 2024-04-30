package com.springboot.connectmate.enums;

import lombok.Getter;

@Getter
public enum MetricCategory {
    SL("Service Level"),
    FCR("First Contact Resolution"),
    ACR("Abandonment Call Rate"),
    OCC("Occupancy"),
    SA("Schedule Adherence"),
    ASA("Average Speed Answer"),
    AHT("Average Handle Time"),
    VFR("Virtual Floor Reconfiguration");

    private final String description;

    MetricCategory(String description) {
        this.description = description;
    }
}
