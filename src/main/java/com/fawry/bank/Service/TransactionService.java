package com.fawry.bank.Service;

import com.fawry.bank.entities.DepositRequest;
import com.fawry.bank.entities.Transaction;
import com.fawry.bank.entities.WithdrawRequest;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions();

    Transaction deposit(DepositRequest request);

    Transaction withdraw(WithdrawRequest request);

    Transaction getTransactionById(Long id);

    void deleteTransaction(Long id);

    List<Transaction> getTransactionsByAccountId(Long accountId);
}
