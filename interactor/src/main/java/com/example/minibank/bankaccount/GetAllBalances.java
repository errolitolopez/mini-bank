package com.example.minibank.bankaccount;

import com.example.minibank.bankaccount.dto.response.BankAccountResponse;

import java.util.Set;

public interface GetAllBalances {

    Set<BankAccountResponse> execute(String referenceNo);
}
