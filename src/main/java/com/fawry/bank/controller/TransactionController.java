package com.fawry.bank.controller;

import org.springframework.web.bind.annotation.*;

import com.fawry.bank.service.impl.TransactionServiceImpl;
import com.fawry.bank.entities.DepositRequest;
import com.fawry.bank.entities.Transaction;
import com.fawry.bank.entities.WithdrawRequest;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/bank/transactions")
public class TransactionController {
    private final TransactionServiceImpl transactionService;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("")
    public List<Transaction> getTransactions() {
        return transactionService.getAllTransactions();
    }

    @PostMapping("/deposit")
    public Transaction deposit(@RequestBody DepositRequest request) {
        return transactionService.deposit(request);
    }

    @PostMapping("/withdraw")
    public Transaction withdraw(@RequestBody WithdrawRequest request) {
        return transactionService.withdraw(request);
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    @GetMapping("/account/{accountId}")
    public List<Transaction> getTransactionsByAccountId(@PathVariable Long accountId) {
        return transactionService.getTransactionsByAccountId(accountId);
    }
}
