package com.practice.expensetracker.controller;

import com.practice.expensetracker.dto.ExpenseDto;
import com.practice.expensetracker.entity.Expense;
import com.practice.expensetracker.services.expense.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<?> postExpense(@RequestBody ExpenseDto expenseDto){
        Expense createdExpense = expenseService.postExpense(expenseDto);
        if(createdExpense != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllExpenses(){
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<?> getExpenseById(@PathVariable Long expenseId){
        try{
            return ResponseEntity.ok(expenseService.getExpenseById(expenseId));
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable Long id, @RequestBody ExpenseDto expenseDto){
        try{
            return ResponseEntity.ok(expenseService.updateExpense(id, expenseDto));
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id){
        try{
            expenseService.deleteExpense(id);
            return ResponseEntity.ok(null);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
}
