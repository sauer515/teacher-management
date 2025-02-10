package com.example.demo.model;

public enum TeacherCondition {
    PRESENT("obecny"),
    BUSINESS_TRIP("delegacja"),
    SICK("chory"),
    ABSENT("nieobecny");

    private final String description;

    TeacherCondition(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}