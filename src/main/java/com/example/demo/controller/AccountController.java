package com.example.demo.controller;

import com.example.demo.Service.AccountService;
import com.example.demo.entities.Account;
import com.example.demo.entities.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    public List<Account> getAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        account.setId(null);
        return accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable("id") Long id) {
        return accountService.getAccountById(id).orElse(null);
    }
    @GetMapping("cardNumber/{id}")
    public String getCardNumberByAccountId(@PathVariable("id")Long id){
        return accountService.getAccountById(id).map(Account::getCardNumber).orElse(null);
    }
    @GetMapping("balance/{id}")
    public Double getBalanceByAccountId(@PathVariable("id")Long id){
        return accountService.getAccountById(id).map(Account::getBalance).orElse(null);
    }

    @GetMapping("/{email}")
    public Account getAccountByEmail(@PathVariable("email") String email) {
        return accountService.getAccountByEmail(email).orElse(null);
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
