package com.aracelyjacinto.expenses.controller;

import com.aracelyjacinto.expenses.models.User;
import com.aracelyjacinto.expenses.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {
  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/users")
  public List<User> findAllUsers() {
    return this.userRepository.findAll();
  }

  @GetMapping("/user/{id}")

  @PostMapping("/user")
  public User addUser(@RequestBody User user) {
    return this.userRepository.save(user);
  }
}
