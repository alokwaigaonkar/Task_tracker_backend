package com.project.taskTracker.dtos;

import java.util.List;
import java.util.Map;

public class AvailableMonthsResponse {

    // key = year, value = list of months (1–12)
    private Map<Integer, List<Integer>> years;

    public AvailableMonthsResponse(Map<Integer, List<Integer>> years) {
        this.years = years;
    }

    public Map<Integer, List<Integer>> getYears() {
        return years;
    }
}