package com.fernando.fincore.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @GetMapping("/demo")
    public Account getDemoAccount() {
        return new Account("FC100001", AccountType.CURRENT);
    }
}
