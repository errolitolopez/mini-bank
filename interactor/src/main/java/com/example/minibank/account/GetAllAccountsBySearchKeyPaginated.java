package com.example.minibank.account;

import com.example.minibank.account.dto.response.AccountResponse;
import com.example.minibank.commons.dto.response.PageDTO;
import org.springframework.data.domain.PageRequest;

public interface GetAllAccountsBySearchKeyPaginated {

    PageDTO<AccountResponse> execute(String searchKey, PageRequest pageRequest);
}
