package com.fernando.fincore.transaction;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "bank_transactions")
public class BankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    private String fromAccountNumber;

    private String toAccountNumber;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    protected BankTransaction() {


    }
    public BankTransaction(
            TransactionType type,
            BigDecimal amount,
            String fromAccountNumber,
            String toAccountNumber) {

        this.type = type;
        this.amount = amount;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.createdAt = Instant.now();
    }
    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
