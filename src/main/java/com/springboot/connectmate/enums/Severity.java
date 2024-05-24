package com.springboot.connectmate.enums;

public enum Severity {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL,
    UNKNOWN;

    public enum ApiType {
        MetricData,
        MetricDataV2,
        CurrentMetric;
    }
}
