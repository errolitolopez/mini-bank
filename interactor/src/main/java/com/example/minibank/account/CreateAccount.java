package com.example.minibank.account;

import com.example.minibank.account.dto.request.CreateAccountParam;
import com.example.minibank.account.dto.response.AccountResponse;

public interface CreateAccount {

    AccountResponse execute(CreateAccountParam createAccountParam);
}
