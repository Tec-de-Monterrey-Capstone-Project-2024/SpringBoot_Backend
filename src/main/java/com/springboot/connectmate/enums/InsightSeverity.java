package com.springboot.connectmate.enums;

public enum InsightSeverity {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL,
    UNKNOWN;

    public static InsightSeverity fromString(String impact) {
        if (impact == null) {
            return UNKNOWN;
        }
        switch (impact.toUpperCase()) {
            case "LOW":
                return LOW;
            case "MEDIUM":
                return MEDIUM;
            case "HIGH":
                return HIGH;
            case "CRITICAL":
                return CRITICAL;
            default:
                return UNKNOWN;
        }
    }

}
