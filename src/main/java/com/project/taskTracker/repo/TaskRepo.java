package com.project.taskTracker.repo;

import com.project.taskTracker.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {

    List<Task> findByDailyGoalId(Long dailyGoalId);

    @Query("""
        select count(t)
        from Task t
        join t.dailyGoal dg
        join dg.studyDay sd
        where sd.month = :month and sd.year = :year
    """)
    long countTotalTasksForMonth(
            @Param("month") int month,
            @Param("year") int year
    );

    @Query("""
        select count(t)
        from Task t
        join t.dailyGoal dg
        join dg.studyDay sd
        where sd.month = :month
          and sd.year = :year
          and t.completed = true
    """)
    long countCompletedTasksForMonth(
            @Param("month") int month,
            @Param("year") int year
    );
}
