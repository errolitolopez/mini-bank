package com.example.minibank.account;

import com.example.minibank.account.dto.response.AccountResponse;

import java.util.List;

public interface GetAllAccounts {

    List<AccountResponse> execute();
}
