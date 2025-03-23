package com.example.demo.Service;

import com.example.demo.Repository.AccountRepository;
import com.example.demo.entities.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(UUID id) {
        return accountRepository.findById(id);
    }

    public Optional<Account> getAccountByCardNumber(String cardNumber) {
        return accountRepository.findByCardNumber(cardNumber);
    }

    public Optional<Account> getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(UUID id) {
        accountRepository.deleteById(id);
    }

    public Account updateAccount(UUID id, Account account) {
        return accountRepository.save(account);
    }
}
