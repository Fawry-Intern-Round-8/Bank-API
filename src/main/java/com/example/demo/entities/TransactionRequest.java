package com.example.demo.entities;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {
    private Long accountId;
    private String type;    
    private double amount;  
    private String notes;  
    private Date timestamp; 
}