package com.project.taskTracker;

import java.time.LocalDate;

import com.project.taskTracker.models.DailyGoal;
import com.project.taskTracker.models.StudyDay;
import com.project.taskTracker.models.Task;
import com.project.taskTracker.repo.StudyDayRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initInitialData(StudyDayRepo studyDayRepo) {
        return args -> {

            // ✅ Do NOTHING if data already exists
            if (studyDayRepo.count() > 0) {
                return;
            }

            // =========================
            // 1️⃣ Create StudyDay (today)
            // =========================
            LocalDate today = LocalDate.now();

            StudyDay studyDay = new StudyDay(
                    today,
                    today.getMonthValue(),
                    today.getYear(),
                    today.get(java.time.temporal.WeekFields.ISO.weekOfMonth())
            );

            // =========================
            // 2️⃣ Create a Daily Goal
            // =========================
            DailyGoal goal = new DailyGoal(
                    "DSA Practice",
                    120
            );

            // =========================
            // 3️⃣ Create Tasks
            // =========================
            Task task1 = new Task("Arrays & Basics", 45);
            task1.markCompleted(); // completed task

            Task task2 = new Task("Binary Search", 30);
            // not completed

            // =========================
            // 4️⃣ Build relationships
            // =========================
            goal.addTask(task1);
            goal.addTask(task2);

            studyDay.addGoal(goal);

            // =========================
            // 5️⃣ Save (cascade saves all)
            // =========================
            studyDayRepo.save(studyDay);

            System.out.println("✅ Initial demo data created for " + today);
        };
    }
}
