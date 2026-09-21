package com.fernando.fincore.account;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {


    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }
    @GetMapping("/{accountNumber}")
    public Account getAccount(@PathVariable String accountNumber) {

        Account account = accountService.getAccount(accountNumber);

        if (account == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Account not found"
            );
        }

        return account;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(
            @Valid @RequestBody CreateAccountRequest request) {

        Account account = accountService.createAccount(request);

        if (account == null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Account already exists"
            );
        }

        return account;
    }

    @PostMapping("/{accountNumber}/deposit")
    public Account deposit(
            @PathVariable String accountNumber,
            @Valid @RequestBody MoneyRequest request) {

        Account account = accountService.deposit(
                accountNumber,
                request.amount()
        );

        if (account == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Account not found"
            );
        }

        return account;
    }

    @PostMapping("/{accountNumber}/withdraw")
    public Account withdraw(
            @PathVariable String accountNumber,
            @Valid @RequestBody MoneyRequest request) {

        try {
            Account account = accountService.withdraw(
                    accountNumber,
                    request.amount()
            );

            if (account == null) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account not found"
                );
            }

            return account;

        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    exception.getMessage()
            );
        }
    }
}
