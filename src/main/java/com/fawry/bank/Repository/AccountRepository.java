package com.fawry.bank.Repository;

import com.fawry.bank.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCardNumber(String cardNumber);
    Optional<Account> findByEmail(String email);
}
