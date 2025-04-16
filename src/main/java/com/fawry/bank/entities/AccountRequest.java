package com.fawry.bank.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String passwordHash;
}
