package com.project.taskTracker.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "daily-goal"
)
@Data
public class DailyGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private int targetMinutes;
    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "study_day_id")
    private StudyDay studyDay;
    @OneToMany(
            mappedBy = "dailyGoal",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Task> tasks = new ArrayList<>();
    protected DailyGoal() {}

    public DailyGoal(String title , int targetMinutes){
        this.title = title;
        this.targetMinutes = targetMinutes;
    }
    void attachTo(StudyDay studyDay) {
        this.studyDay = studyDay;
    }
    public void addTask(Task task) {
        task.attachTo(this);
        tasks.add(task);
    }

}
