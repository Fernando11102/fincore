package com.fernando.fincore.transfer;

import com.fernando.fincore.account.Account;

import java.math.BigDecimal;

public class TransferService {

    public void transfer(Account fromAccount, Account toAccount, BigDecimal amount) {
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }
}
