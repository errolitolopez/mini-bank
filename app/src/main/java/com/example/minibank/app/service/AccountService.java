package com.example.minibank.app.service;

import com.example.minibank.account.dto.response.AccountResponse;
import com.example.minibank.app.dto.request.CreateAccountForm;
import com.example.minibank.app.dto.request.UpdateAccountForm;
import com.example.minibank.commons.dto.request.PageRequestParam;
import com.example.minibank.commons.dto.response.PageDTO;
import com.example.minibank.commons.dto.response.ServiceResponse;

public interface AccountService {

    PageDTO<AccountResponse> getAllAccounts(PageRequestParam pageRequestParam);

    ServiceResponse<AccountResponse> createAccount(CreateAccountForm createAccountForm);

    ServiceResponse<AccountResponse> updateAccount(long id, UpdateAccountForm updateAccountForm);

    ServiceResponse<AccountResponse> deleteAccount(long id);

    ServiceResponse<AccountResponse> getAccountById(long id);

    ServiceResponse<AccountResponse> getAccountByEmail(String email);

    ServiceResponse<AccountResponse> getAccountByReferenceNo(String referenceNo);

    ServiceResponse<AccountResponse> getAccountByName(String name);

    PageDTO<AccountResponse> searchAccounts(String searchKey, PageRequestParam pageRequestParam);
}
