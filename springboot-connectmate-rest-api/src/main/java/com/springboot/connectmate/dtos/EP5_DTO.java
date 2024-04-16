package com.example.demo.dto;

public class EP5_DTO{
    private int id;
    private String name;
    private String description;
    private int limitInSeconds;
    private int answeredCalls;
    private int totalCalls;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLimitInSeconds() {
        return limitInSeconds;
    }

    public int getAnsweredCalls() {
        return answeredCalls;
    }

    public int getTotalCalls() {
        return totalCalls;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLimitInSeconds(int limitInSeconds) {
        this.limitInSeconds = limitInSeconds;
    }

    public void setAnsweredCalls(int answeredCalls) {
        this.answeredCalls = answeredCalls;
    }

    public void setTotalCalls(int totalCalls) {
        this.totalCalls = totalCalls;
    }

}