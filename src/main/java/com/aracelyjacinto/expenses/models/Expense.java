package com.aracelyjacinto.expenses.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Expense {
  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonBackReference
  private User user;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private String category;
  private Float amount;
  @JsonFormat(pattern="yyyy-MM-dd")
  private LocalDate date;
}
