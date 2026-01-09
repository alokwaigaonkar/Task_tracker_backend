package com.project.taskTracker.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "study-day",
        uniqueConstraints = @UniqueConstraint(columnNames = {"study-date"})
)
@Data
public class StudyDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "study-date" , nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int month;

    @Column(nullable = false)
    private int year;

    @Column(name = "week-of-month" , nullable = false)
    private int weekOfMonth;

    @OneToMany(
            mappedBy = "studyDay",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<DailyGoal> dailyGoals = new ArrayList<>();

    protected StudyDay(){}

    public  StudyDay(LocalDate date){
        this.date= date;
        this.month = date.getMonthValue();
        this.year = date.getYear();
        this.weekOfMonth = calculateWeekOfMonth(date);
    }

    private int calculateWeekOfMonth(LocalDate date){
        return  (date.getDayOfMonth() -1 ) / 7+1;
    }

    public void addGoal(DailyGoal dailyGoal){
        dailyGoal.attachTo(this);
        dailyGoals.add(dailyGoal);
    }
    public StudyDay(
            LocalDate date,
            int month,
            int year,
            int weekOfMonth
    ) {
        this.date = date;
        this.month = month;
        this.year = year;
        this.weekOfMonth = weekOfMonth;
    }


}
