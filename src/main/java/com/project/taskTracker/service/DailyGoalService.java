package com.project.taskTracker.service;

import com.project.taskTracker.models.DailyGoal;
import com.project.taskTracker.models.StudyDay;
import com.project.taskTracker.repo.DailyGoalRepo;
import com.project.taskTracker.repo.StudyDayRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class DailyGoalService {

    private final StudyDayRepo studyDayRepository;
    private final DailyGoalRepo dailyGoalRepository;

    public DailyGoalService(
            StudyDayRepo studyDayRepository,
            DailyGoalRepo dailyGoalRepository
    ) {
        this.studyDayRepository = studyDayRepository;
        this.dailyGoalRepository = dailyGoalRepository;
    }

    public void addGoal(LocalDate date, String title, int targetMinutes) {

        StudyDay day = studyDayRepository.findByDate(date)
                .orElseThrow(() ->
                        new IllegalArgumentException("Study day not found: " + date)
                );

        DailyGoal goal = new DailyGoal(title, targetMinutes);
        day.addGoal(goal);
    }

    public void deleteGoal(Long goalId) {
        dailyGoalRepository.deleteById(goalId);
    }
}