package com.fernando.fincore.account;
import com.fernando.fincore.transaction.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    public AccountService(
            AccountRepository accountRepository,
            TransactionService transactionService) {

        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
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

    @Transactional
    public Account deposit(String accountNumber, BigDecimal amount) {

        Account account = accountRepository.findById(accountNumber)
                .orElse(null);

        if (account == null) {
            return null;
        }

        account.deposit(amount);

        Account savedAccount = accountRepository.save(account);

        transactionService.recordDeposit(
                accountNumber,
                amount
        );

        return savedAccount;
    }

    @Transactional
    public Account withdraw(String accountNumber, BigDecimal amount) {

        Account account = accountRepository.findById(accountNumber)
                .orElse(null);

        if (account == null) {
            return null;
        }

        account.withdraw(amount);

        Account savedAccount = accountRepository.save(account);

        transactionService.recordWithdrawal(
                accountNumber,
                amount
        );

        return savedAccount;
    }
}