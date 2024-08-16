package com.practice.expensetracker.entity;

import com.practice.expensetracker.dto.IncomeDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer amount;

    private LocalDate date;

    private String category;

    public IncomeDto getIncomeDto(){
        IncomeDto incomeDto = new IncomeDto();

        incomeDto.setId(id);
        incomeDto.setTitle(title);
        incomeDto.setDescription(description);
        incomeDto.setAmount(amount);
        incomeDto.setDate(date);
        incomeDto.setCategory(category);
        return incomeDto;
    }

}
