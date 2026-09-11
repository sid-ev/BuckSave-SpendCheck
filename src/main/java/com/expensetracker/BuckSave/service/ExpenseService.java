package com.expensetracker.BuckSave.service;

import com.expensetracker.BuckSave.dto.ExpenseRequest;
import com.expensetracker.BuckSave.dto.ExpenseResponse;
import com.expensetracker.BuckSave.entity.Category;
import com.expensetracker.BuckSave.entity.Expense;
import com.expensetracker.BuckSave.entity.User;
import com.expensetracker.BuckSave.repository.CategoryRepository;
import com.expensetracker.BuckSave.repository.ExpenseRepository;
import com.expensetracker.BuckSave.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
//temp user
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            CategoryRepository categoryRepository,
            UserRepository userRepository) {

        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public ExpenseResponse createExpense(ExpenseRequest request) {



        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        User user = userRepository.findById(2L) //Temp User with 1L
                .orElseThrow(() -> new RuntimeException("User not found"));


        Expense expense = new Expense();

        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setDate(request.getDate());
        expense.setCategory(category);

        expense.setUser(user);//re

        Expense savedExpense = expenseRepository.save(expense);

        return new ExpenseResponse(
                savedExpense.getId(),
                savedExpense.getAmount(),
                savedExpense.getDescription(),
                savedExpense.getDate(),
                savedExpense.getCategory().getId(),
                savedExpense.getCategory().getName()
        );
    }

    public List<ExpenseResponse> getAllExpenses() {

        List<Expense> expenses = expenseRepository.findAll();

        return expenses.stream()
                .map(expense -> new ExpenseResponse(
                        expense.getId(),
                        expense.getAmount(),
                        expense.getDescription(),
                        expense.getDate(),
                        expense.getCategory().getId(),
                        expense.getCategory().getName()
                ))
                .toList();
    }

    public ExpenseResponse getExpenseById(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        return new ExpenseResponse(
                expense.getId(),
                expense.getAmount(),
                expense.getDescription(),
                expense.getDate(),
                expense.getCategory().getId(),
                expense.getCategory().getName()
        );
    }

    public ExpenseResponse updateExpense(Long id, ExpenseRequest request) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setDate(request.getDate());
        expense.setCategory(category);

        Expense updatedExpense = expenseRepository.save(expense);

        return new ExpenseResponse(
                updatedExpense.getId(),
                updatedExpense.getAmount(),
                updatedExpense.getDescription(),
                updatedExpense.getDate(),
                updatedExpense.getCategory().getId(),
                updatedExpense.getCategory().getName()
        );
    }

    public void deleteExpense(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expenseRepository.delete(expense);
    }
}
