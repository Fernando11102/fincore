package com.fernando.fincore.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankTransactionRepository
        extends JpaRepository<BankTransaction, Long> {

    List<BankTransaction>
    findByFromAccountNumberOrToAccountNumberOrderByCreatedAtDesc(
            String fromAccountNumber,
            String toAccountNumber
    );
}
