package com.example.demo.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "transactions")
@Entity
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account; 

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String notes;

    @Column(nullable = false)
    private Date timestamp;
}