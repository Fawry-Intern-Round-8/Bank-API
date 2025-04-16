package com.fawry.bank.Service;

import java.util.List;
import java.util.Optional;

import com.fawry.bank.entities.Account;
import com.fawry.bank.entities.AccountRequest;

public interface AccountService {
    public List<Account> getAllAccounts();
    public Optional<Account> getAccountById(Long id);
    public Optional<Account> getAccountByCardNumber(String cardNumber);
    public Optional<Account> getAccountByEmail(String email);
    public Account createAccount(AccountRequest accountRequest);
    public void deleteAccount(Long id);
    public Account updateAccount(Long id, AccountRequest accountRequest);
}