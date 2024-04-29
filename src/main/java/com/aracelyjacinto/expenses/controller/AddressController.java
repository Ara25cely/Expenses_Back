package com.aracelyjacinto.expenses.controller;

import com.aracelyjacinto.expenses.models.Address;
import com.aracelyjacinto.expenses.repository.AddressRepository;
import com.aracelyjacinto.expenses.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AddressController {
  private final AddressRepository addressRepository;
  private final UserRepository userRepository;

  public AddressController(AddressRepository addressRepository, UserRepository userRepository) {
    this.addressRepository = addressRepository;
    this.userRepository = userRepository;
  }

  @GetMapping("/address/{userId}")
  public ResponseEntity<Address> getUserAddressById(
      @PathVariable(name = "userId") int userId
  ) {
    return userRepository.findById(userId)
        .map(user -> ResponseEntity.ok().body(user.getAddress()))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/address/{userId}")
  public ResponseEntity<Address> saveUserAddress(
      @PathVariable(name = "userId") int userId,
      @RequestBody Address address
  ) {
    return userRepository.findById(userId)
        .map(user -> {
          addressRepository.save(address);
          user.setAddress(address);
          userRepository.save(user);
          return ResponseEntity.ok().body(address);
        })
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("address/{userId}")
  public ResponseEntity<Address> updateUserAddress(
      @PathVariable(name = "userId") int userId,
      @RequestBody Address newAddress
  ) {
    return userRepository.findById(userId)
        .map(user -> {
          Address currentAddress = user.getAddress();
          if (currentAddress != null) {
            currentAddress.setStreet(newAddress.getStreet());
            currentAddress.setExtNumber(newAddress.getExtNumber());
            currentAddress.setPostalCode(newAddress.getPostalCode());
            currentAddress.setState(newAddress.getState());
            currentAddress.setCountry(newAddress.getCountry());
            addressRepository.save(currentAddress);
            return ResponseEntity.ok().body(currentAddress);
          } else {
            // If person does not have an existing address, save the new address
            addressRepository.save(newAddress);
            user.setAddress(newAddress);
            userRepository.save(user);
            return ResponseEntity.ok().body(newAddress);
          }
        })
        .orElse(ResponseEntity.notFound().build());
  }
}
