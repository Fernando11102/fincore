package com.fernando.fincore;

import com.fernando.fincore.account.Account;
import com.fernando.fincore.account.AccountType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class FinCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinCoreApplication.class, args);
        Account account = new Account("FC100001", AccountType.CURRENT);
        account.deposit(new BigDecimal("500.00"));
        account.withdraw(new BigDecimal("125.50"));
        System.out.println("Balance: £" + account.getBalance());
    }

}
