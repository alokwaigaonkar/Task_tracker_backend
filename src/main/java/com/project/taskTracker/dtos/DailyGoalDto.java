package com.project.taskTracker.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DailyGoalDto {

    private Long goalId;
    private String title;
    private int targetMinutes;
    private List<TaskDto> tasks;

    public DailyGoalDto(
            Long goalId,
            String title,
            int targetMinutes,
            List<TaskDto> tasks
    ) {
        this.goalId = goalId;
        this.title = title;
        this.targetMinutes = targetMinutes;
        this.tasks = tasks;
    }

}
