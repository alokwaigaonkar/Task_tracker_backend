package com.project.taskTracker.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title ;
    @Column(nullable = false)
    private boolean completed;
    @Column(nullable = false)
    private  int estimatedMinutes;
    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "daily_goal_id")
    private DailyGoal dailyGoal;

    protected Task() {}

    public Task(String title, int estimatedMinutes) {
        this.title = title;
        this.estimatedMinutes = estimatedMinutes;
    }

    void attachTo(DailyGoal dailyGoal) {
        this.dailyGoal = dailyGoal;
    }

    public void markCompleted() {
        this.completed = true;
    }

}
