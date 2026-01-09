package com.project.taskTracker.controller;

import com.project.taskTracker.dtos.CreateTaskRequest;
import com.project.taskTracker.dtos.UpdateTaskCompletionRequest;
import com.project.taskTracker.service.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /* ===============================
       Add task to a goal
       =============================== */

    @PostMapping("/goals/{goalId}/tasks")
    public void addTask(
            @PathVariable Long goalId,
            @RequestBody CreateTaskRequest request
    ) {
        taskService.addTask(
                goalId,
                request.getTitle(),
                request.getEstimatedMinutes()
        );
    }

    /* ===============================
       Update completion
       =============================== */

    @PatchMapping("/tasks/{taskId}/completion")
    public void updateCompletion(
            @PathVariable Long taskId,
            @RequestBody UpdateTaskCompletionRequest request
    ) {
        taskService.updateCompletion(taskId, request.isCompleted());
    }

    /* ===============================
       Delete task
       =============================== */

    @DeleteMapping("/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }
}