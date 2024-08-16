package com.practice.expensetracker.controller;

import com.practice.expensetracker.dto.IncomeDto;
import com.practice.expensetracker.entity.Income;
import com.practice.expensetracker.services.income.IncomeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/income")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping
    public ResponseEntity<?> postIncome(@RequestBody IncomeDto incomeDto) {
        Income income = incomeService.postIncome(incomeDto);
        if(income != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(income);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllIncomes() {
        return ResponseEntity.ok(incomeService.getAllIncomes());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getIncomeById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(incomeService.getIncomeById(id));
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateIncome(@PathVariable Long id,@RequestBody IncomeDto incomeDto) {
        try{
            return ResponseEntity.ok(incomeService.updateIncome(id, incomeDto));
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable Long id) {
        try{
            incomeService.deleteIncome(id);
            return ResponseEntity.ok(null);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
}
