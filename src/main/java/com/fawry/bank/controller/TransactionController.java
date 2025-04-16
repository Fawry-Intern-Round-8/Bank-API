package com.fawry.bank.controller;

import com.fawry.bank.Service.TransactionService;
import com.fawry.bank.entities.Transaction;
import com.fawry.bank.entities.TransactionRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @GetMapping("")
    public List<Transaction> getTransactions() {
        return transactionService.getAllTransactions();
    }
    @PostMapping
    public Transaction createTransaction(@RequestBody TransactionRequest request) {
        return transactionService.createTransaction(request);
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