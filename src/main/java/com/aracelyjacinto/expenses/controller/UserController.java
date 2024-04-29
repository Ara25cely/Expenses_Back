package com.aracelyjacinto.expenses.controller;

import com.aracelyjacinto.expenses.models.User;
import com.aracelyjacinto.expenses.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/users")
  public List<User> findAllUsers() {
    return this.userRepository.findAll();
  }

  @PostMapping("/user")
  public User addUser(@RequestBody User user) {
    return this.userRepository.save(user);
  }
}
