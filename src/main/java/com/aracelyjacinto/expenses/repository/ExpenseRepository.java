package com.aracelyjacinto.expenses.repository;


import com.aracelyjacinto.expenses.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
}
