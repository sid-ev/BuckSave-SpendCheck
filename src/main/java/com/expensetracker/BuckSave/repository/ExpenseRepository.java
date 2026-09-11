package com.expensetracker.BuckSave.repository;

import com.expensetracker.BuckSave.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
