package com.project.taskTracker.dtos;

import java.time.LocalDate;
import java.util.List;

public class StudyDayDetailDto {

    private LocalDate date;
    private List<DailyGoalDto> goals;

    public StudyDayDetailDto(LocalDate date, List<DailyGoalDto> goals) {
        this.date = date;
        this.goals = goals;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<DailyGoalDto> getGoals() {
        return goals;
    }
}
