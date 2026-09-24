package com.fernando.fincore.transfer;

import com.fernando.fincore.account.Account;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import com.fernando.fincore.account.AccountRepository;
import org.springframework.transaction.annotation.Transactional;
import com.fernando.fincore.transaction.TransactionService;
import com.fernando.fincore.transaction.TransactionService;

@Service
public class TransferService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;


    public TransferService(
            AccountRepository accountRepository,
            TransactionService transactionService) {

        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    public void transfer(
            Account fromAccount,
            Account toAccount,
            BigDecimal amount) {

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }
    @Transactional
    public boolean transfer(
            String fromAccountNumber,
            String toAccountNumber,
            BigDecimal amount) {

        if (fromAccountNumber.equals(toAccountNumber)) {
            throw new IllegalArgumentException(
                    "Source and destination accounts must be different"
            );
        }

        Account fromAccount = accountRepository
                .findById(fromAccountNumber)
                .orElse(null);

        Account toAccount = accountRepository
                .findById(toAccountNumber)
                .orElse(null);

        if (fromAccount == null || toAccount == null) {
            return false;
        }

        transfer(fromAccount, toAccount, amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        transactionService.recordTransfer(
                fromAccountNumber,
                toAccountNumber,
                amount
        );

        return true;
    }
}
