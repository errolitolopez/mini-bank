package com.example.minibank.account;

import com.example.minibank.account.dto.response.AccountResponse;

public interface GetAccountByEmail {

    AccountResponse execute(String email);
}
