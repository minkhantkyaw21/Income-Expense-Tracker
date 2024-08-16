package com.practice.expensetracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeDto {
    private Long id;

    private String title;

    private String description;

    private Integer amount;

    private LocalDate date;

    private String category;
}
