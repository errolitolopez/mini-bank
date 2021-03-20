package com.example.minibank.domain;

import com.example.minibank.domain.model.Transaction;

import java.util.List;

public interface TransactionGateway {

    List<Transaction> getAllTransactionHistoriesByAccountNumber(String accountNumber);

    Transaction saveTransaction(Transaction transaction);
}
