package com.project.taskTracker.service;

import com.project.taskTracker.dtos.MonthlyStatsDto;
import com.project.taskTracker.repo.TaskRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StatsService {

    private final TaskRepo taskRepo;

    public StatsService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public MonthlyStatsDto getMonthlyStats(int year, int month) {

        long total = taskRepo
                .countTotalTasksForMonth(month, year);

        long completed = taskRepo
                .countCompletedTasksForMonth(month, year);

        return new MonthlyStatsDto(
                year,
                month,
                (int) total,
                (int) completed
        );
    }
}
