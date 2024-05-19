package com.aracelyjacinto.expenses.controller;

import com.aracelyjacinto.expenses.models.Income;
import com.aracelyjacinto.expenses.repository.IncomeRepository;
import com.aracelyjacinto.expenses.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class IncomeController {
  private final IncomeRepository incomeRepository;
  private final UserRepository userRepository;

  public IncomeController(IncomeRepository incomeRepository, UserRepository userRepository) {
    this.incomeRepository = incomeRepository;
    this.userRepository = userRepository;
  }

  @GetMapping("/incomes/{userId}")
  public ResponseEntity<List<Income>> getIncomesByUserId(@PathVariable int userId) {
    return userRepository.findById(userId).map(user -> ResponseEntity.ok().body(user.getIncomes()))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/income/{userId}")
  public ResponseEntity<Income> addIncomeToUser(@PathVariable int userId, @RequestBody Income income) {
    return userRepository.findById(userId).map(user -> {
          income.setUser(user);
          incomeRepository.save(income);
          return ResponseEntity.ok().body(income);
        }
    ).orElse(ResponseEntity.notFound().build());
  }
}
