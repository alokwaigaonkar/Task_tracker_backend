package com.project.taskTracker.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MonthDaySummaryDto {

    private LocalDate date;
    private int weekOfMonth;
    private int totalTasks;
    private int completedTasks;
    private int completionPercentage;

    public MonthDaySummaryDto(
            LocalDate date,
            int weekOfMonth,
            int totalTasks,
            int completedTasks
    ) {
        this.date = date;
        this.weekOfMonth = weekOfMonth;
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
        this.completionPercentage =
                totalTasks == 0 ? 0 : (completedTasks * 100) / totalTasks;
    }


}
