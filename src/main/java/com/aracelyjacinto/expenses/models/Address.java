package com.aracelyjacinto.expenses.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String street;
  private int extNumber;
  private String postalCode;
  private String state;
  private String country;
}