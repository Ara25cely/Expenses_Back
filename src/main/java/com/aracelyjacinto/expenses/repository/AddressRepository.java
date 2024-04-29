package com.aracelyjacinto.expenses.repository;


import com.aracelyjacinto.expenses.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
