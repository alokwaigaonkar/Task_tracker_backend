package com.project.taskTracker.repo;

import com.project.taskTracker.models.DailyGoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyGoalRepo extends JpaRepository<DailyGoal,Long> {
    List<DailyGoal> findByStudyDayId(Long StudyDayId);
}
