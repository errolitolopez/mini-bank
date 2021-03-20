package com.example.minibank.account;

import com.example.minibank.account.dto.response.AccountResponse;

public interface DeleteAccountById {

    AccountResponse execute(long id);
}
