package com.aracelyjacinto.expenses.controller;

import com.aracelyjacinto.expenses.models.User;
import com.aracelyjacinto.expenses.repository.UserRepository;
import com.aracelyjacinto.expenses.service.EmailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
  private final UserRepository userRepository;
  private final EmailService emailService;

  public UserController(UserRepository userRepository, EmailService emailService) {
    this.userRepository = userRepository;
    this.emailService = emailService;
  }

  @PostMapping("user/signup")
  public String signupUser(
      @RequestBody User user
  ) {
    String fromEmail = "420097703@pcpuma.acatlan.unam.mx";
    if (user != null) {
      userRepository.save(user);
      emailService.sendEmail(user.getEmail(), fromEmail, "Equipo de Expenses: Bienvenido");
      return "Saved successfully";
    } else {
      return "invalid user";
    }
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
