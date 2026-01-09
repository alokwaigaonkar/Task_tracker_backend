package com.project.taskTracker.service;

import com.project.taskTracker.models.DailyGoal;
import com.project.taskTracker.models.Task;
import com.project.taskTracker.repo.DailyGoalRepo;
import com.project.taskTracker.repo.TaskRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskService {

    private final DailyGoalRepo dailyGoalRepo;
    private final TaskRepo taskRepo;

    public TaskService(
            DailyGoalRepo dailyGoalRepo,
            TaskRepo taskRepo
    ) {
        this.dailyGoalRepo = dailyGoalRepo;
        this.taskRepo = taskRepo;
    }

    public void addTask(Long goalId, String title, int estimatedMinutes) {

        DailyGoal goal = dailyGoalRepo.findById(goalId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Goal not found: " + goalId)
                );

        Task task = new Task(title, estimatedMinutes);
        goal.addTask(task);
    }

    public void updateCompletion(Long taskId, boolean completed) {

        Task task = taskRepo.findById(taskId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Task not found: " + taskId)
                );

        if (completed) {
            task.markCompleted();
        } else {
            task.setCompleted(false); // add setter or method
        }
    }

    public void deleteTask(Long taskId) {
        taskRepo.deleteById(taskId);
    }
}
