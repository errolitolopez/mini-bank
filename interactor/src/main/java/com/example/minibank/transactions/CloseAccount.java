package com.example.minibank.transactions;

import com.example.minibank.transactions.dto.response.TransactionResponse;

public interface CloseAccount {

    TransactionResponse execute(String accountNumber);
}
