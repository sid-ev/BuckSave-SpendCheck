package com.expensetracker.BuckSave.Controller;

import com.expensetracker.BuckSave.dto.ExpenseRequest;
import com.expensetracker.BuckSave.dto.ExpenseResponse;
import com.expensetracker.BuckSave.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(
            @RequestBody ExpenseRequest request) {

        return ResponseEntity.ok(
                expenseService.createExpense(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getAllExpenses() {

        return ResponseEntity.ok(
                expenseService.getAllExpenses()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> getExpenseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                expenseService.getExpenseById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> updateExpense(
            @PathVariable Long id,
            @RequestBody ExpenseRequest request) {

        return ResponseEntity.ok(
                expenseService.updateExpense(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(
            @PathVariable Long id) {

        expenseService.deleteExpense(id);

        return ResponseEntity.noContent().build();
    }
}