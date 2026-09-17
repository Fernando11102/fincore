package com.fernando.fincore.account;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @GetMapping
    public List<Account> getAccounts() {

        Account currentAccount =
                new Account("FC100001", AccountType.CURRENT);

        Account savingsAccount =
                new Account("FC100002", AccountType.SAVINGS);

        return List.of(currentAccount, savingsAccount);
    }
    @GetMapping("/{accountNumber}")
    public Account getAccount(@PathVariable String accountNumber) {

        return getAccounts()
                .stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Account not found"
                        )
                );
    }
}
