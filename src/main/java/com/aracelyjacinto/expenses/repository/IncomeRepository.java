package com.aracelyjacinto.expenses.repository;


import com.aracelyjacinto.expenses.models.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Integer> {
}
