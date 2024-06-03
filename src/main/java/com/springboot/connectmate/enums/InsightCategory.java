package com.springboot.connectmate.enums;

public enum InsightCategory {
    CRITICAL,
    UNSATISFACTORY,
    BELOW_EXPECTATIONS,
    EXCEEDS_EXPECTATIONS,
    OUTSTANDING,
    PIONEERING,
    UNKNOWN;

    public static InsightCategory fromString(String impact) {
        if (impact == null) {
            return UNKNOWN;
        }
        switch (impact.toUpperCase()) {
            case "CRITICAL":
                return CRITICAL;
            case "UNSATISFACTORY":
                return UNSATISFACTORY;
            case "BELOW_EXPECTATIONS":
                return BELOW_EXPECTATIONS;
            case "EXCEEDS_EXPECTATIONS":
                return EXCEEDS_EXPECTATIONS;
            case "OUTSTANDING":
                return OUTSTANDING;
            case "PIONEERING":
                return PIONEERING;
            default:
                return UNKNOWN;
        }
    }

}
