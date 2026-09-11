package com.expensetracker.BuckSave.repository;

import com.expensetracker.BuckSave.entity.SpendingLimit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingLimitRepository
        extends JpaRepository<SpendingLimit, Long> {
}