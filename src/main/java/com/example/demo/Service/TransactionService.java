package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.TransactionRepository;
import com.example.demo.entities.Account;
import com.example.demo.entities.Transaction;
import com.example.demo.entities.TransactionRequest;

@Service
public class TransactionService {
    @Autowired
    private final TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository=transactionRepository;
    }

    public List<Transaction>getAllTransactions(){
        return transactionRepository.findAll();
    }
    public Transaction createTransaction(TransactionRequest request){
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if(request.getType().equalsIgnoreCase("WITHDRAW") && account.getBalance() < request.getAmount()){
            throw new RuntimeException("Insufficient funds");
        }else if(request.getType().equalsIgnoreCase("DEPOSIT")&& account.getBalance()>=request.getAmount()){
            account.setBalance(account.getBalance() + request.getAmount());
            accountRepository.save(account);
        }
        else if(request.getType().equalsIgnoreCase("WITHDRAW")){
            account.setBalance(account.getBalance() - request.getAmount());
            accountRepository.save(account);
        }
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType(request.getType());
        transaction.setAmount(request.getAmount());
        transaction.setNotes(request.getNotes());
        transaction.setTimestamp(new Date());

        return transactionRepository.save(transaction);
    }
    public Transaction getTransactionById(Long id){
        return transactionRepository.findById(id).orElse(null);
    }
    public void deleteTransaction(Long id){
        transactionRepository.deleteById(id);
    }
    public List<Transaction> getTransactionsByAccountId(Long accountId){
        List <Transaction> list = new ArrayList<Transaction>();
        for(Transaction transaction : transactionRepository.findAll()){
            if(transaction.getAccount().getId().equals(accountId)){
                list.add(transaction);
            }
        }
        return list;
    }
}