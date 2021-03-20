package com.example.minibank.app.service;

import com.example.minibank.app.dto.request.BankAccountForm;
import com.example.minibank.app.dto.response.BankAccountResource;
import com.example.minibank.commons.dto.response.ServiceResponse;

public interface BankAccountService {

    ServiceResponse<BankAccountResource> createBankAccount(String referenceNo, BankAccountForm bankAccountForm);
}
