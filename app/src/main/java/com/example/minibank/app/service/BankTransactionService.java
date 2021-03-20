package com.example.minibank.app.service;

import com.example.minibank.app.dto.request.CashInForm;
import com.example.minibank.app.dto.request.CashOutForm;
import com.example.minibank.app.dto.response.BankTransactionResource;
import com.example.minibank.commons.dto.response.ServiceResponse;

import java.util.List;

public interface BankTransactionService {

    ServiceResponse<List<BankTransactionResource>> getAllBankingTransactionsByAccountNumber(String accountNumber);

    ServiceResponse<BankTransactionResource> cashIn(String accountNumber, CashInForm cashInForm);

    ServiceResponse<BankTransactionResource> cashOut(String accountNumber, CashOutForm cashOutForm);

    ServiceResponse<BankTransactionResource> closeAccount(String accountNumber);
}
