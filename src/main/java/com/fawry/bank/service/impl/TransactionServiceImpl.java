package com.fawry.bank.service.impl;

import com.fawry.bank.entities.Account;
import com.fawry.bank.entities.DepositRequest;
import com.fawry.bank.entities.Transaction;
import com.fawry.bank.entities.WithdrawRequest;
import com.fawry.bank.exceptions.AccountNotFoundException;
import com.fawry.bank.exceptions.InsufficientFundsException;
import com.fawry.bank.repository.AccountRepository;
import com.fawry.bank.repository.TransactionRepository;
import com.fawry.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private final TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    @Transactional
    public Transaction deposit(DepositRequest request) {
        Account account = getAccountByCardNumber(request.getCardNumber());
        account.setBalance(account.getBalance() + request.getAmount());
        accountRepository.save(account);
        return createTransaction(account, "DEPOSIT", request.getAmount(), request.getNotes());
    }

    @Override
    @Transactional
    public Transaction withdraw(WithdrawRequest request) {
        Account account = getAccountByCardNumber(request.getCardNumber());
        validateSufficientBalance(account, request.getAmount());
        account.setBalance(account.getBalance() - request.getAmount());
        accountRepository.save(account);
        return createTransaction(account, "WITHDRAW", request.getAmount(), request.getNotes());
    }

    private Account getAccountByCardNumber(String cardNumber) {
        return accountRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account with card number " + cardNumber + " not found"));
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        List<Transaction> list = new ArrayList<>();
        for (Transaction transaction : transactionRepository.findAll()) {
            if (transaction.getAccount().getId().equals(accountId)) {
                list.add(transaction);
            }
        }
        return list;
    }


    private void validateSufficientBalance(Account account, double amount) {
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds");
        }
    }

    private Transaction createTransaction(Account account, String type,
                                          double amount, String notes) {
        account.setActive(true);
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setNotes(notes);
        transaction.setTimestamp(new Date());
        return transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
