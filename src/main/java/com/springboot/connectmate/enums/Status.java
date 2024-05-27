package com.springboot.connectmate.enums;

public enum Status {
    DONE,
    TO_DO,
    IN_PROGRESS;

    public static Status fromString(String text) {
        for (Status b : Status.values()) {
            if (b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
