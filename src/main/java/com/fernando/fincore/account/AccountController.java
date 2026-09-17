package com.fernando.fincore.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
