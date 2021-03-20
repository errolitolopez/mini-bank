package com.example.minibank.transactions;

import com.example.minibank.transactions.dto.request.CashInParam;
import com.example.minibank.transactions.dto.response.TransactionResponse;

public interface CashIn {

    TransactionResponse execute(String accountNumber, CashInParam cashInParam);

}
