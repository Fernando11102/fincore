package com.fernando.fincore.transaction;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;


@Service
public class TransactionService {

    private final BankTransactionRepository transactionRepository;

    public TransactionService(
            BankTransactionRepository transactionRepository) {

        this.transactionRepository = transactionRepository;
    }
    public BankTransaction recordDeposit(
            String accountNumber,
            BigDecimal amount) {

        BankTransaction transaction = new BankTransaction(
                TransactionType.DEPOSIT,
                amount,
                null,
                accountNumber
        );

        return transactionRepository.save(transaction);
    }

    public BankTransaction recordWithdrawal(
            String accountNumber,
            BigDecimal amount) {

        BankTransaction transaction = new BankTransaction(
                TransactionType.WITHDRAWAL,
                amount,
                accountNumber,
                null
        );

        return transactionRepository.save(transaction);
    }

    public BankTransaction recordTransfer(
            String fromAccountNumber,
            String toAccountNumber,
            BigDecimal amount) {

        BankTransaction transaction = new BankTransaction(
                TransactionType.TRANSFER,
                amount,
                fromAccountNumber,
                toAccountNumber
        );

        return transactionRepository.save(transaction);
    }
    public List<BankTransaction> getTransactionsForAccount(
            String accountNumber) {

        return transactionRepository
                .findByFromAccountNumberOrToAccountNumberOrderByCreatedAtDesc(
                        accountNumber,
                        accountNumber
                );
    }
}
