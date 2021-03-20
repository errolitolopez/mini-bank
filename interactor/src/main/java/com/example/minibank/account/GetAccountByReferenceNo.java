package com.example.minibank.account;

import com.example.minibank.account.dto.response.AccountResponse;

public interface GetAccountByReferenceNo {

    AccountResponse execute(String referenceNo);
}
