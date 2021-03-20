package com.example.minibank.transactions;

import com.example.minibank.transactions.dto.request.CashOutParam;
import com.example.minibank.transactions.dto.response.TransactionResponse;

public interface CashOut {
    TransactionResponse execute(String accountNumber, CashOutParam cashOutParam);
}
