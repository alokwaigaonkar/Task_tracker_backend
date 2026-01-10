package com.project.taskTracker.service;

import com.project.taskTracker.dtos.*;
import com.project.taskTracker.models.DailyGoal;
import com.project.taskTracker.models.StudyDay;
import com.project.taskTracker.models.Task;
import com.project.taskTracker.repo.StudyDayRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudyDayService {

    private final StudyDayRepo studyDayRepo;

    public StudyDayService(StudyDayRepo studyDayRepo) {
        this.studyDayRepo = studyDayRepo;
    }

    /* ===============================
       Sidebar: Year -> Month
       =============================== */

    @Transactional(readOnly = true)
    public AvailableMonthsResponse getAvailableMonths() {
        List<Object[]> rows = studyDayRepo.findAvailableMonths();

        Map<Integer, List<Integer>> map = new LinkedHashMap<>();

        for (Object[] row : rows) {
            int month = (int) row[0];
            int year = (int) row[1];

            map.computeIfAbsent(year, y -> new ArrayList<>()).add(month);
        }

        return new AvailableMonthsResponse(map);
    }

    /* ===============================
       Month View
       =============================== */

    @Transactional(readOnly = true)
    public List<MonthDaySummaryDto> getMonthSummary(int year, int month) {

        List<StudyDay> days =
                studyDayRepo.findMonthGroupedByWeek(month, year);

        return days.stream()
                .map(this::toMonthDaySummary)
                .toList();
    }

    private MonthDaySummaryDto toMonthDaySummary(StudyDay day) {
        int total = 0;
        int completed = 0;

        for (DailyGoal goal : day.getDailyGoals()) {
            for (Task task : goal.getTasks()) {
                total++;
                if (task.isCompleted()) completed++;
            }
        }

        return new MonthDaySummaryDto(
                day.getDate(),
                day.getWeekOfMonth(),
                total,
                completed
        );
    }

    /* ===============================
       Day Detail
       =============================== */

    @Transactional(readOnly = true)
    public StudyDayDetailDto getDayDetail(LocalDate date) {

        StudyDay day = studyDayRepo.findByDate(date)
                .orElseThrow(() ->
                        new IllegalArgumentException("Study day not found: " + date)
                );

        List<DailyGoalDto> goals = day.getDailyGoals().stream()
                .map(this::toDailyGoalDto)
                .toList();

        return new StudyDayDetailDto(day.getDate(), goals);
    }

    /* ===============================
       Create or Load Day
       =============================== */

    public StudyDayDetailDto createOrLoadDay(LocalDate date) {

        StudyDay day = studyDayRepo.findByDate(date).orElseGet(() -> {

            StudyDay newDay = new StudyDay(date);

            // Find yesterday
            LocalDate yesterday = date.minusDays(1);

            studyDayRepo.findByDate(yesterday).ifPresent(prevDay -> {

                for (DailyGoal prevGoal : prevDay.getDailyGoals()) {

                    // Copy goal
                    DailyGoal newGoal = new DailyGoal(
                            prevGoal.getTitle(),
                            prevGoal.getTargetMinutes()
                    );

                    // Copy ALL tasks but reset completion
                    for (Task prevTask : prevGoal.getTasks()) {

                        Task newTask = new Task(
                                prevTask.getTitle(),
                                prevTask.getEstimatedMinutes()
                        );

                        // IMPORTANT: do NOT copy completed state
                        // newTask.completed defaults to false

                        newGoal.addTask(newTask);
                    }

                    newDay.addGoal(newGoal);
                }
            });

            return studyDayRepo.save(newDay);
        });

        return getDayDetail(day.getDate());
    }

    /* ===============================
       Mapping helpers
       =============================== */

    private DailyGoalDto toDailyGoalDto(DailyGoal goal) {
        List<TaskDto> tasks = goal.getTasks().stream()
                .map(this::toTaskDto)
                .toList();

        return new DailyGoalDto(
                goal.getId(),
                goal.getTitle(),
                goal.getTargetMinutes(),
                tasks
        );
    }

    private TaskDto toTaskDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getEstimatedMinutes(),
                task.isCompleted()
        );
    }
}
