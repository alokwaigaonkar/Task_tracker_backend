package com.project.taskTracker.controller;

import com.project.taskTracker.dtos.MonthlyStatsDto;
import com.project.taskTracker.service.StatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/month")
    public MonthlyStatsDto getMonthlyStats(
            @RequestParam int year,
            @RequestParam int month
    ) {
        return statsService.getMonthlyStats(year, month);
    }
}
