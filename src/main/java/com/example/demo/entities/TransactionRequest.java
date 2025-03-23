package com.example.demo.entities;
import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {
    private UUID accountId; 
    private String type;    
    private double amount;  
    private String notes;  
    private Date timestamp; 
}