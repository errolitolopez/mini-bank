package com.example.minibank.bankaccount;

import com.example.minibank.bankaccount.dto.request.CreateBankAccountParam;
import com.example.minibank.bankaccount.dto.response.BankAccountResponse;

public interface CreateBankAccount {

    BankAccountResponse execute(String referenceNo, CreateBankAccountParam createBankAccountParam);
}
