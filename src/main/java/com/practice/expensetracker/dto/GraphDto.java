package com.practice.expensetracker.dto;

import com.practice.expensetracker.entity.Expense;
import com.practice.expensetracker.entity.Income;
import lombok.Data;

import java.util.List;

@Data
public class GraphDto {

    private List<Income> incomeList;

    private List<Expense> expenseList;
}
