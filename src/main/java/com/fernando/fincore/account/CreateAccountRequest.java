package com.fernando.fincore.account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAccountRequest(

        @NotBlank
        String accountNumber,

        @NotNull
        AccountType accountType

) {
}
