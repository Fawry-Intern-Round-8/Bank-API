package com.fawry.bank.Service;

import java.util.List;

import com.fawry.bank.entities.DepositRequest;
import com.fawry.bank.entities.Transaction;
import com.fawry.bank.entities.WithdrawRequest;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    
    Transaction deposit(DepositRequest request);
    
    Transaction withdraw(WithdrawRequest request);
    
    Transaction getTransactionById(Long id);
    
    void deleteTransaction(Long id);
    
    List<Transaction> getTransactionsByAccountId(Long accountId);
}