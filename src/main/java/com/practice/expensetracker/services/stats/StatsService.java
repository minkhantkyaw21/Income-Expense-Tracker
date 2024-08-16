package com.practice.expensetracker.services.stats;

import com.practice.expensetracker.dto.GraphDto;
import com.practice.expensetracker.dto.StatsDto;

public interface StatsService {

    GraphDto getChartData();

    StatsDto getStats();
}
