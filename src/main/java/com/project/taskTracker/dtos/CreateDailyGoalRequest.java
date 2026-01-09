package com.project.taskTracker.dtos;

public class CreateDailyGoalRequest {

    private String title;
    private int targetMinutes;

    public String getTitle() {
        return title;
    }

    public int getTargetMinutes() {
        return targetMinutes;
    }
}
