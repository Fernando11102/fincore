package com.fernando.fincore.transfer;

import com.fernando.fincore.account.Account;
import com.fernando.fincore.account.AccountType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import com.fernando.fincore.account.AccountRepository;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransferServiceTest {

    @Test
    void transferShouldMoveMoneyBetweenAccounts() {

        Account currentAccount =
                new Account("FC100001", AccountType.CURRENT);

        Account savingsAccount =
                new Account("FC100002", AccountType.SAVINGS);

        currentAccount.deposit(new BigDecimal("500.00"));

        AccountRepository accountRepository = mock(AccountRepository.class);

        TransferService transferService =
                new TransferService(accountRepository);

        transferService.transfer(
                currentAccount,
                savingsAccount,
                new BigDecimal("200.00")
        );

        assertEquals(
                new BigDecimal("300.00"),
                currentAccount.getBalance()
        );

        assertEquals(
                new BigDecimal("200.00"),
                savingsAccount.getBalance()
        );
    }

    @Test
    void transferShouldFailWhenFundsAreInsufficient() {

        Account currentAccount =
                new Account("FC100001", AccountType.CURRENT);

        Account savingsAccount =
                new Account("FC100002", AccountType.SAVINGS);

        currentAccount.deposit(new BigDecimal("100.00"));

        AccountRepository accountRepository = mock(AccountRepository.class);

        TransferService transferService =
                new TransferService(accountRepository);

        assertThrows(
                IllegalArgumentException.class,
                () -> transferService.transfer(
                        currentAccount,
                        savingsAccount,
                        new BigDecimal("200.00")
                )
        );

        assertEquals(
                new BigDecimal("100.00"),
                currentAccount.getBalance()
        );

        assertEquals(
                BigDecimal.ZERO,
                savingsAccount.getBalance()
        );
    }
}
