package com.fernando.fincore.account;

import org.springframework.stereotype.Service;

import java.util.List;
import java.math.BigDecimal;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(String accountNumber) {
        return accountRepository.findById(accountNumber)
                .orElse(null);
    }
    public Account createAccount(CreateAccountRequest request) {

        if (accountRepository.existsById(request.accountNumber())) {
            return null;
        }

        Account account = new Account(
                request.accountNumber(),
                request.accountType()
        );

        return accountRepository.save(account);
    }
    public Account deposit(String accountNumber, BigDecimal amount) {

        Account account = accountRepository.findById(accountNumber)
                .orElse(null);

        if (account == null) {
            return null;
        }

        account.deposit(amount);

        return accountRepository.save(account);
    }
    public Account withdraw(String accountNumber, BigDecimal amount) {

        Account account = accountRepository.findById(accountNumber)
                .orElse(null);

        if (account == null) {
            return null;
        }

        account.withdraw(amount);

        return accountRepository.save(account);
    }
}