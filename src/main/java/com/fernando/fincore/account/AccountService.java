package com.fernando.fincore.account;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    public List<Account> getAccounts() {

        Account currentAccount =
                new Account("FC100001", AccountType.CURRENT);

        Account savingsAccount =
                new Account("FC100002", AccountType.SAVINGS);

        return List.of(currentAccount, savingsAccount);
    }
    public Account getAccount(String accountNumber) {

        return getAccounts()
                .stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
    }
}