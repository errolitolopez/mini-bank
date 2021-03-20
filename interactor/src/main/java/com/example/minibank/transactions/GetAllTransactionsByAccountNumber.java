package com.example.minibank.transactions;

import com.example.minibank.transactions.dto.response.TransactionResponse;

import java.util.List;

public interface GetAllTransactionsByAccountNumber {

    List<TransactionResponse> execute(String accountNumber);
}
