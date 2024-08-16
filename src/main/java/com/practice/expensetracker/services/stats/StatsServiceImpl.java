package com.practice.expensetracker.services.stats;

import com.practice.expensetracker.dto.GraphDto;
import com.practice.expensetracker.dto.StatsDto;
import com.practice.expensetracker.entity.Expense;
import com.practice.expensetracker.entity.Income;
import com.practice.expensetracker.repository.ExpenseRepository;
import com.practice.expensetracker.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService{

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    public GraphDto getChartData(){
        LocalDate endDate= LocalDate.now();
        LocalDate startDate=endDate.minusDays(1);

        GraphDto graphDto = new GraphDto();
        graphDto.setExpenseList(expenseRepository.findByDateBetween(startDate,endDate));
        graphDto.setIncomeList(incomeRepository.findByDateBetween(startDate,endDate));

        return graphDto;
    }

    public StatsDto getStats(){
        Double totalIncome=incomeRepository.sumAllAmounts();
        Double totalExpense=expenseRepository.sumAllAmounts();

        Optional<Income> optionalIncome=incomeRepository.findFirstByOrderByDateDesc();
        Optional<Expense> optionalExpense=expenseRepository.findFirstByOrderByDateDesc();

        StatsDto statsDto = new StatsDto();
        statsDto.setExpense(totalExpense);
        statsDto.setIncome(totalIncome);

//        if(optionalIncome.isPresent()){
//            statsDto.setLatestIncome(optionalIncome.get());
//        }
        optionalExpense.ifPresent(statsDto::setLatestExpense);
        optionalIncome.ifPresent(statsDto::setLatestIncome);

        statsDto.setBalance(totalIncome-totalExpense);
        List<Income> incomeList=incomeRepository.findAll();
        List<Expense> expenseList=expenseRepository.findAll();

        OptionalDouble minIncome=incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome=incomeList.stream().mapToDouble(Income::getAmount).max();
        OptionalDouble minExpense=expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense=expenseList.stream().mapToDouble(Expense::getAmount).max();

        statsDto.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);
        statsDto.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);
        statsDto.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : null);
        statsDto.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : null);

        return statsDto;
    }
}
