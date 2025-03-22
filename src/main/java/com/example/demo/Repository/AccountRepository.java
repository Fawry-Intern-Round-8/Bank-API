package com.example.demo.Repository;

import com.example.demo.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByCardNumber(String cardNumber);
    Optional<Account> findByEmail(String email);
}
