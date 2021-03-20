package com.example.minibank.account;

import com.example.minibank.account.dto.response.AccountResponse;

import java.util.Optional;

public interface GetAccountByAccountNumber {
    Optional<AccountResponse> execute(String accountNumber);
}
