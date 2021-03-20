package com.example.minibank.app.service.impl;

import com.example.minibank.account.GetAccountByReferenceNo;
import com.example.minibank.account.dto.response.AccountResponse;
import com.example.minibank.app.dto.request.BankAccountForm;
import com.example.minibank.app.dto.response.BankAccountResource;
import com.example.minibank.app.service.BankAccountService;
import com.example.minibank.bankaccount.CreateBankAccount;
import com.example.minibank.bankaccount.GetAllBalances;
import com.example.minibank.bankaccount.dto.request.CreateBankAccountParam;
import com.example.minibank.bankaccount.dto.response.BankAccountResponse;
import com.example.minibank.commons.constant.SourceAccountType;
import com.example.minibank.commons.dto.response.ServiceResponse;
import com.example.minibank.commons.exception.NotFoundException;
import com.example.minibank.commons.exception.NotValidException;
import com.example.minibank.exchangerate.GetAllExchangeRates;
import com.example.minibank.exchangerate.dto.response.ExchangeRateResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BankAccountServiceImpl implements BankAccountService {

    private ModelMapper mapper = new ModelMapper();
    private final GetAccountByReferenceNo getAccountByReferenceNo;
    private final CreateBankAccount createBankAccount;
    private final GetAllExchangeRates getAllExchangeRates;
    private final GetAllBalances getAllBalances;

    @Override
    public ServiceResponse<BankAccountResource> createBankAccount(String referenceNo, BankAccountForm bankAccountForm) {

        final Optional<ExchangeRateResponse> exchangeRateResponseOptional = getAllExchangeRates.execute()
                .stream()
                .filter(o -> o.getCurrency().equalsIgnoreCase(bankAccountForm.getCurrency()))
                .findFirst();

        if (!exchangeRateResponseOptional.isPresent()) {
            throw new NotFoundException("Invalid currency [" + bankAccountForm.getCurrency() + "].");
        }

        final Optional<SourceAccountType> sourceAccountTypeOptional = Arrays
                .stream(SourceAccountType.values())
                .filter(o -> o.name().equalsIgnoreCase(bankAccountForm.getType()))
                .findFirst();

        if (!sourceAccountTypeOptional.isPresent()) {
            throw new NotValidException("Invalid account type [" + bankAccountForm.getType() + "]." +
                    " Please choose between CURRENT, SALARY or SAVINGS");
        }

        final Optional<BankAccountResponse> existingBankAccountOptional = getAllBalances.execute(referenceNo)
                .stream()
                .filter(o -> o.getType().name().equalsIgnoreCase(bankAccountForm.getType()))
                .findFirst();

        if (existingBankAccountOptional.isPresent()) {
            throw new NotValidException("Account type [" + bankAccountForm.getType() + "] already existing.");
        }

        final AccountResponse accountResponse = getAccountByReferenceNo.execute(referenceNo);

        CreateBankAccountParam createBankAccountParam = mapper.map(bankAccountForm, CreateBankAccountParam.class);
        final BankAccountResponse bankAccountResponse = createBankAccount.execute(referenceNo, createBankAccountParam);

        BankAccountResource bankAccountResource = new BankAccountResource();
        bankAccountResource.setName(accountResponse.getName());
        bankAccountResource.setAccountNumber(bankAccountResponse.getAccountNumber());
        bankAccountResource.setCurrency(bankAccountResponse.getCurrency());
        bankAccountResource.setAmount(bankAccountResponse.getAmount());
        bankAccountResource.setType(bankAccountResponse.getType());
        bankAccountResource.setDate(new Date());
        return new ServiceResponse<>(bankAccountResource);
    }
}
