package com.fawry.bank.service.impl;

import com.fawry.bank.entities.Account;
import com.fawry.bank.entities.AccountRequest;
import com.fawry.bank.exceptions.CardGenerationException;
import com.fawry.bank.exceptions.DuplicateAccountException;
import com.fawry.bank.repository.AccountRepository;
import com.fawry.bank.service.AccountService;
import com.fawry.bank.utils.CardNumberGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Optional<Account> getAccountByCardNumber(String cardNumber) {
        return accountRepository.findByCardNumber(cardNumber);
    }

    public Optional<Account> getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Account createAccount(AccountRequest accountRequest) {
        Account account = new Account();
        account.setName(accountRequest.getName());
        account.setPasswordHash(accountRequest.getPasswordHash());
        account.setEmail(accountRequest.getEmail());
        String cardNumber;
        int attempts = 0;
        final int MAX_ATTEMPTS = 5;

        do {
            cardNumber = CardNumberGenerator.generate();
            attempts++;

            if (attempts >= MAX_ATTEMPTS) {
                throw new CardGenerationException("Failed to generate unique card number after " + MAX_ATTEMPTS + " attempts");
            }
        } while (accountRepository.existsByCardNumber(cardNumber));
        log.info("cardNumber : " + cardNumber);
        account.setCardNumber(cardNumber);

        if (accountRepository.existsByEmail(account.getEmail())) {
            throw new DuplicateAccountException("Email already exists");
        }
        account.setBalance(0.0);


        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Account updateAccount(Long id, AccountRequest accountRequest) {
        Account account = accountRepository.getReferenceById(id);
        account.setEmail(accountRequest.getEmail());
        account.setName(accountRequest.getName());
        account.setPasswordHash(accountRequest.getPasswordHash());
        return accountRepository.save(account);
    }
}
