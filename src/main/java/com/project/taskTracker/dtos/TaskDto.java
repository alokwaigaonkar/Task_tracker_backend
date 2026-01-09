package com.project.taskTracker.dtos;

import lombok.Data;

@Data
public class TaskDto {

    private Long taskId;
    private String title;
    private int estimatedMinutes;
    private boolean completed;

    public TaskDto(
            Long taskId,
            String title,
            int estimatedMinutes,
            boolean completed
    ) {
        this.taskId = taskId;
        this.title = title;
        this.estimatedMinutes = estimatedMinutes;
        this.completed = completed;
    }


}