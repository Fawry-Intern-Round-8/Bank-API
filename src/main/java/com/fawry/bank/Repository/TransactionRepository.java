package com.fawry.bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fawry.bank.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}