package com.fernando.fincore.transaction;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(
            TransactionService transactionService) {

        this.transactionService = transactionService;
    }

    @GetMapping("/account/{accountNumber}")
    public List<BankTransaction> getTransactionsForAccount(
            @PathVariable String accountNumber) {

        return transactionService
                .getTransactionsForAccount(accountNumber);
    }
}
