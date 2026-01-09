package com.project.taskTracker.controller;

import com.project.taskTracker.dtos.CreateDailyGoalRequest;
import com.project.taskTracker.service.DailyGoalService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class DailyGoalController {

    private final DailyGoalService dailyGoalService;

    public DailyGoalController(DailyGoalService dailyGoalService) {
        this.dailyGoalService = dailyGoalService;
    }

    /* ===============================
       Add goal to a day
       =============================== */

    @PostMapping("/study-days/{date}/goals")
    public void addGoal(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date,
            @RequestBody CreateDailyGoalRequest request
    ) {
        dailyGoalService.addGoal(
                date,
                request.getTitle(),
                request.getTargetMinutes()
        );
    }

    /* ===============================
       Delete goal
       =============================== */

    @DeleteMapping("/goals/{goalId}")
    public void deleteGoal(@PathVariable Long goalId) {
        dailyGoalService.deleteGoal(goalId);
    }
}
