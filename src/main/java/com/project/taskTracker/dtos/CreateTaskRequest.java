package com.project.taskTracker.dtos;

import lombok.Data;

@Data
public class CreateTaskRequest {
    private String title;
    private int estimatedMinutes;

}
