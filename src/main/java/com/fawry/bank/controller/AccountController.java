package com.fawry.bank.controller;

import com.fawry.bank.service.impl.AccountServiceImpl;
import com.fawry.bank.entities.Account;
import com.fawry.bank.entities.AccountRequest;
import com.fawry.bank.entities.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/bank/accounts")
public class AccountController {

    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    public List<Account> getAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest accountRequest) {
        Account existingAccount = accountService.getAccountByEmail(accountRequest.getEmail()).orElse(null);
        if (existingAccount != null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("this email already exists");
        }
        return ResponseEntity.ok(accountService.createAccount(accountRequest));
    }

    @GetMapping("cardNumber/{id}")
    public String getCardNumberByAccountId(@PathVariable("id") Long id) {
        return accountService.getAccountById(id).map(Account::getCardNumber).orElse(null);
    }

    @GetMapping("balance/{id}")
    public Double getBalanceByAccountId(@PathVariable("id") Long id) {
        return accountService.getAccountById(id).map(Account::getBalance).orElse(null);
    }

    @GetMapping("/{email}")
    public Account getAccountByEmail(@PathVariable("email") String email) {
        return accountService.getAccountByEmail(email).orElse(null);
    }

    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id, @RequestBody AccountRequest accountRequest) {
        return accountService.updateAccount(id, accountRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        System.out.println("Login request: " + request.getEmail() + ", " + request.getPassword());

        Account account = accountService.getAccountByEmail(request.getEmail()).orElse(null);

        if (account == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        } else if (!account.getPasswordHash().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        } else {
            return ResponseEntity.ok(account);
        }
    }
}
