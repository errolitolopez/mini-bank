package com.example.minibank.bankaccount;

import com.example.minibank.bankaccount.dto.response.BankAccountResponse;

import java.util.Optional;

public interface GetBalanceByAccountNumber {

    Optional<BankAccountResponse> execute(String accountNumber);
}
