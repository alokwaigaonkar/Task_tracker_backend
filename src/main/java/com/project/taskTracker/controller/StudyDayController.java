package com.project.taskTracker.controller;

import com.project.taskTracker.dtos.AvailableMonthsResponse;
import com.project.taskTracker.dtos.CreateStudyDayRequest;
import com.project.taskTracker.dtos.MonthDaySummaryDto;
import com.project.taskTracker.dtos.StudyDayDetailDto;
import com.project.taskTracker.service.StudyDayService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/study-days")
public class StudyDayController {

    private final StudyDayService studyDayService;

    public StudyDayController(StudyDayService studyDayService) {
        this.studyDayService = studyDayService;
    }

    /* ===============================
       Sidebar: Year -> Month
       =============================== */

    @GetMapping("/available-months")
    public AvailableMonthsResponse getAvailableMonths() {
        return studyDayService.getAvailableMonths();
    }

    /* ===============================
       Month View
       =============================== */

    @GetMapping("/month")
    public List<MonthDaySummaryDto> getMonthSummary(
            @RequestParam int year,
            @RequestParam int month
    ) {
        return studyDayService.getMonthSummary(year, month);
    }

    /* ===============================
       Day Detail
       =============================== */

    @GetMapping("/{date}")
    public StudyDayDetailDto getDayDetail(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ) {
        return studyDayService.getDayDetail(date);
    }

    /* ===============================
       Create or Load Day
       =============================== */

    @PostMapping
    public StudyDayDetailDto createOrLoadDay(
            @RequestBody CreateStudyDayRequest request
    ) {
        return studyDayService.createOrLoadDay(request.getDate());
    }
}
