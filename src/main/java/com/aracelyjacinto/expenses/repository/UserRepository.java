package com.aracelyjacinto.expenses.repository;


import com.aracelyjacinto.expenses.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
