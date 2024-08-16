package com.practice.expensetracker.dto;

import com.practice.expensetracker.entity.Expense;
import com.practice.expensetracker.entity.Income;
import lombok.Data;

@Data
public class StatsDto {

    private Double income;
    private Double expense;
    private Income latestIncome;
    private Expense latestExpense;

    private Double balance;
    private Double minIncome;
    private Double maxIncome;
    private Double minExpense;
    private Double maxExpense;
}
