package com.example.minibank.account;

import com.example.minibank.account.dto.response.AccountResponse;

public interface GetAccountById {

    AccountResponse execute(long id);
}
