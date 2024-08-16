package com.practice.expensetracker.services.expense;

import com.practice.expensetracker.dto.ExpenseDto;
import com.practice.expensetracker.entity.Expense;

import java.util.List;

public interface ExpenseService {

    Expense postExpense(ExpenseDto expenseDto);

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    Expense updateExpense(Long id, ExpenseDto expenseDto);

    void deleteExpense(Long id);
}
