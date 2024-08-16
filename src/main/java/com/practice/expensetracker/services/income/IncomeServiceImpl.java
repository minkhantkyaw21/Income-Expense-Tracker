package com.practice.expensetracker.services.income;

import com.practice.expensetracker.dto.IncomeDto;
import com.practice.expensetracker.entity.Income;
import com.practice.expensetracker.repository.IncomeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;

    //Creating post income API call
    public Income postIncome(IncomeDto incomeDto) {
        return saveOrUpdateIncome(new Income(), incomeDto);
    }

    private Income saveOrUpdateIncome(Income income, IncomeDto incomeDto) {
        income.setTitle(incomeDto.getTitle());
        income.setDescription(incomeDto.getDescription());
        income.setAmount(incomeDto.getAmount());
        income.setCategory(incomeDto.getCategory());
        income.setDate(incomeDto.getDate());
        return incomeRepository.save(income);
    }
// Creating get all incomes
    public List<IncomeDto> getAllIncomes(){
        return incomeRepository.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .map(Income::getIncomeDto)
                .collect(Collectors.toList());
    }
    //Creating get income by id
    public IncomeDto getIncomeById(Long id) {
        Optional<Income> income = incomeRepository.findById(id);
        if (income.isPresent()) {
            return income.get().getIncomeDto();
        }else{
            throw new EntityNotFoundException("Income not found with id: " + id);
        }
    }
    //Creating update income API call
    public Income updateIncome(Long id,IncomeDto incomeDto){
        Optional<Income> income = incomeRepository.findById(id);
        if(income.isPresent()){
            return saveOrUpdateIncome(income.get(), incomeDto);
        }else{
            throw new EntityNotFoundException("Income is not present with id " + id);
        }
    }
    //Creating delete income API call
    public void deleteIncome(Long id){
        Optional<Income> income = incomeRepository.findById(id);
        if(income.isPresent()){
            incomeRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Income not found with id " + id);
        }
    }
}
