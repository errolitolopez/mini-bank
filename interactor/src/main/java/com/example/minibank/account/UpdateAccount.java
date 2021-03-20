package com.example.minibank.account;

import com.example.minibank.account.dto.request.UpdateAccountParam;
import com.example.minibank.account.dto.response.AccountResponse;

public interface UpdateAccount {

    AccountResponse execute(long id, UpdateAccountParam updateAccountParam);
}
