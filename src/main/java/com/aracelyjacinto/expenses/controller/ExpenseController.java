package com.aracelyjacinto.expenses.controller;

import com.aracelyjacinto.expenses.models.Expense;
import com.aracelyjacinto.expenses.repository.ExpenseRepository;
import com.aracelyjacinto.expenses.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ExpenseController {
  private final ExpenseRepository expenseRepository;
  private final UserRepository userRepository;

  public ExpenseController(ExpenseRepository expenseRepository, UserRepository userRepository) {
    this.expenseRepository = expenseRepository;
    this.userRepository = userRepository;
  }

  @GetMapping("/expenses/{userId}")
  public ResponseEntity<List<Expense>> getExpensesByUserId(@PathVariable int userId) {
    return userRepository.findById(userId).map(user -> ResponseEntity.ok().body(user.getExpenses()))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/expense/{userId}")
  public ResponseEntity<Expense> addExpenseToUser(@PathVariable int userId, @RequestBody Expense expense) {
    return userRepository.findById(userId).map(user -> {
          expense.setUser(user);
          expenseRepository.save(expense);
          return ResponseEntity.ok().body(expense);
        }
    ).orElse(ResponseEntity.notFound().build());
  }
}
