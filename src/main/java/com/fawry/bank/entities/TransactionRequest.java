package com.fawry.bank.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransactionRequest {
    private Long accountId;
    private String type;
    private double amount;
    private String notes;
    private Date timestamp;
}
