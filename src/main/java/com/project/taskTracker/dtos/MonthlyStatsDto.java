package com.project.taskTracker.dtos;

import lombok.Data;

@Data
public class MonthlyStatsDto {

    private int year;
    private int month;
    private int totalTasks;
    private int completedTasks;
    private int completionPercentage;

    public MonthlyStatsDto(
            int year,
            int month,
            int totalTasks,
            int completedTasks
    ) {
        this.year = year;
        this.month = month;
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
        this.completionPercentage =
                totalTasks == 0 ? 0 : (completedTasks * 100) / totalTasks;
    }


}
