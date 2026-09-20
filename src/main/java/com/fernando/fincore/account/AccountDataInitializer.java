package com.fernando.fincore.account;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountDataInitializer implements CommandLineRunner {

    private final AccountRepository accountRepository;

    public AccountDataInitializer(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) {

        if (accountRepository.count() == 0) {
            accountRepository.save(
                    new Account("FC100001", AccountType.CURRENT)
            );

            accountRepository.save(
                    new Account("FC100002", AccountType.SAVINGS)
            );
        }
    }
}