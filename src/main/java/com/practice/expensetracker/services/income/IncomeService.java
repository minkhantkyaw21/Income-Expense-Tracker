package com.practice.expensetracker.services.income;

import com.practice.expensetracker.dto.IncomeDto;
import com.practice.expensetracker.entity.Income;

import java.util.List;

public interface IncomeService {

    Income postIncome(IncomeDto incomeDto);

    List<IncomeDto> getAllIncomes();

    IncomeDto getIncomeById(Long id);

    Income updateIncome(Long id,IncomeDto incomeDto);

    void deleteIncome(Long id);
}
