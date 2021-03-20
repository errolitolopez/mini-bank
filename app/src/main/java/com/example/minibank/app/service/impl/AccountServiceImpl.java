package com.example.minibank.app.service.impl;

import com.example.minibank.account.CreateAccount;
import com.example.minibank.account.DeleteAccountById;
import com.example.minibank.account.GetAccountByEmail;
import com.example.minibank.account.GetAccountById;
import com.example.minibank.account.GetAccountByReferenceNo;
import com.example.minibank.account.GetAllAccounts;
import com.example.minibank.account.GetAllAccountsBySearchKeyPaginated;
import com.example.minibank.account.GetAllAccountsPaginated;
import com.example.minibank.account.UpdateAccount;
import com.example.minibank.account.dto.request.CreateAccountParam;
import com.example.minibank.account.dto.request.UpdateAccountParam;
import com.example.minibank.account.dto.response.AccountResponse;
import com.example.minibank.app.dto.request.CreateAccountForm;
import com.example.minibank.app.dto.request.UpdateAccountForm;
import com.example.minibank.app.service.AccountService;
import com.example.minibank.commons.dto.request.PageRequestParam;
import com.example.minibank.commons.dto.response.PageDTO;
import com.example.minibank.commons.dto.response.ServiceResponse;
import com.example.minibank.commons.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.minibank.commons.constant.GenericStatus.ACTIVE;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AccountServiceImpl implements AccountService {

    private final ModelMapper mapper = new ModelMapper();

    private final GetAllAccounts getAllAccounts;
    private final GetAllAccountsPaginated getAllAccountsPaginated;
    private final GetAllAccountsBySearchKeyPaginated getAllAccountsBySearchKeyPaginated;
    private final GetAccountById getAccountById;
    private final GetAccountByEmail getAccountByEmail;
    private final GetAccountByReferenceNo getAccountByReferenceNo;
    private final CreateAccount createAccount;
    private final DeleteAccountById deleteAccountById;
    private final UpdateAccount updateAccount;

    @Override
    public PageDTO<AccountResponse> getAllAccounts(PageRequestParam pageRequestParam) {
        return getAllAccountsPaginated.execute(pageRequestParam.getPageRequest());
    }

    @Override
    public ServiceResponse<AccountResponse> createAccount(CreateAccountForm createAccountForm) {
        CreateAccountParam createAccountParam = mapper.map(createAccountForm, CreateAccountParam.class);

        final AccountResponse accountResponse = createAccount.execute(createAccountParam);

        final ServiceResponse<AccountResponse> serviceResponse = new ServiceResponse<>();
        serviceResponse.setData(accountResponse);
        serviceResponse.setMessage("Transaction successful. Account successfully created.");
        return serviceResponse;
    }

    @Override
    public ServiceResponse<AccountResponse> updateAccount(long id, UpdateAccountForm updateAccountForm) {

        UpdateAccountParam updateAccountParam = mapper.map(updateAccountForm, UpdateAccountParam.class);

        final AccountResponse accountResponse = updateAccount.execute(id, updateAccountParam);
        final ServiceResponse<AccountResponse> serviceResponse = new ServiceResponse<>();
        serviceResponse.setData(accountResponse);
        serviceResponse.setMessage("Transaction successful. Account successfully updated.");
        return serviceResponse;
    }

    @Override
    public ServiceResponse<AccountResponse> deleteAccount(long id) {
        final AccountResponse accountResponse = deleteAccountById.execute(id);
        return new ServiceResponse<>(accountResponse);
    }

    @Override
    public ServiceResponse<AccountResponse> getAccountById(long id) {
        final AccountResponse accountResponse = getAccountById.execute(id);
        return new ServiceResponse<>(accountResponse);
    }

    @Override
    public ServiceResponse<AccountResponse> getAccountByEmail(String email) {
        final AccountResponse accountResponse = getAccountByEmail.execute(email);
        return new ServiceResponse<>(accountResponse);
    }

    @Override
    public ServiceResponse<AccountResponse> getAccountByName(String name) {

        final Optional<AccountResponse> accountResponseOptional = getAllAccounts.execute()
                .stream()
                .filter(o -> o.getStatus().equals(ACTIVE) && o.getName().toLowerCase().contains(name.toLowerCase()))
                .findFirst();

        if (accountResponseOptional.isPresent()) {
            return new ServiceResponse<>(accountResponseOptional.get());
        }
        throw new NotFoundException("No records found for [" + name + "].");
    }


    @Override
    public ServiceResponse<AccountResponse> getAccountByReferenceNo(String referenceNo) {
        final AccountResponse accountResponse = getAccountByReferenceNo.execute(referenceNo);
        return new ServiceResponse<>(accountResponse);

    }

    @Override
    public PageDTO<AccountResponse> searchAccounts(String searchKey, PageRequestParam pageRequestParam) {
        return getAllAccountsBySearchKeyPaginated.execute(searchKey, pageRequestParam.getPageRequest());
    }
}
