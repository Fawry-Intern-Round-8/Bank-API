package com.fawry.bank.service;

import com.fawry.bank.entities.Account;
import com.fawry.bank.entities.AccountRequest;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> getAllAccounts();

    Optional<Account> getAccountById(Long id);

    Optional<Account> getAccountByCardNumber(String cardNumber);

    Optional<Account> getAccountByEmail(String email);

    Account createAccount(AccountRequest accountRequest);

    void deleteAccount(Long id);

    Account updateAccount(Long id, AccountRequest accountRequest);
}
