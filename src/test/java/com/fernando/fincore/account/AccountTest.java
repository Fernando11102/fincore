package com.fernando.fincore.account;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {

    @Test
    void newAccountShouldStartWithZeroBalance() {

        Account account = new Account("FC100001", AccountType.CURRENT);

        assertEquals(BigDecimal.ZERO, account.getBalance());
    }
    @Test
    void depositShouldIncreaseBalance() {

        Account account = new Account("FC100001", AccountType.CURRENT);

        account.deposit(new BigDecimal("500.00"));

        assertEquals(new BigDecimal("500.00"), account.getBalance());
    }
    @Test
    void withdrawalShouldReduceBalance() {

        Account account = new Account("FC100001", AccountType.CURRENT);

        account.deposit(new BigDecimal("500.00"));
        account.withdraw(new BigDecimal("125.50"));

        assertEquals(new BigDecimal("374.50"), account.getBalance());
    }
    @Test
    void withdrawalShouldFailWhenFundsAreInsufficient() {

        Account account = new Account("FC100001", AccountType.CURRENT);

        account.deposit(new BigDecimal("500.00"));

        assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(new BigDecimal("600.00"))
        );
    }
    @Test
    void failedWithdrawalShouldNotChangeBalance() {

        Account account = new Account("FC100001", AccountType.CURRENT);

        account.deposit(new BigDecimal("500.00"));

        assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(new BigDecimal("600.00"))
        );

        assertEquals(new BigDecimal("500.00"), account.getBalance());
    }
    @Test
    void depositShouldFailWhenAmountIsNegative() {

        Account account = new Account("FC100001", AccountType.CURRENT);

        assertThrows(
                IllegalArgumentException.class,
                () -> account.deposit(new BigDecimal("-100.00"))
        );
    }
}