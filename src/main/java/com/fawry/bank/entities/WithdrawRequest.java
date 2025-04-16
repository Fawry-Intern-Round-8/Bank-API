package com.fawry.bank.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawRequest {
    @NotBlank
    @Pattern(regexp = "^[0-9]{16}$")
    private String cardNumber;

    @Positive
    private double amount;

    @Size(max = 255)
    private String notes;
}
