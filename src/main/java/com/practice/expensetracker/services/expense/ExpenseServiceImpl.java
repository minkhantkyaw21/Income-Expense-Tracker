package com.practice.expensetracker.services.expense;

import com.practice.expensetracker.dto.ExpenseDto;
import com.practice.expensetracker.entity.Expense;
import com.practice.expensetracker.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    //Creating post expense API call
    @Override
    public Expense postExpense(ExpenseDto expenseDto) {
        return saveOrUpdateExpense(new Expense(), expenseDto);
    }

    public Expense saveOrUpdateExpense(Expense expense, ExpenseDto expenseDto) {
        expense.setTitle(expenseDto.getTitle());
        expense.setDescription(expenseDto.getDescription());
        expense.setCategory(expenseDto.getCategory());
       expense.setAmount(expenseDto.getAmount());
       expense.setDate(expenseDto.getDate());

        return expenseRepository.save(expense);
    }

    //Creating get all expense API call with sorting
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }

    //Creating get expense by id API call
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if(expense.isPresent()) {
            return expense.get();
        }else{
            throw new EntityNotFoundException("Expense not found");
        }

    }

    //Creating update expense by id API call
    public Expense updateExpense(Long id, ExpenseDto expenseDto) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if(expense.isPresent()) {
            return saveOrUpdateExpense(expense.get(), expenseDto);
        }else{
            throw new EntityNotFoundException("Expense not found with id: " + id);
        }
    }

    //Create delete expense expense API call
    public void deleteExpense(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if(expense.isPresent()) {
            expenseRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Expense not found with id: " + id);
        }
    }

}
