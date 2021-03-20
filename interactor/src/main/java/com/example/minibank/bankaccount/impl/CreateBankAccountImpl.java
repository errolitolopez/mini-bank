package com.example.minibank.bankaccount.impl;

import com.example.minibank.bankaccount.CreateBankAccount;
import com.example.minibank.bankaccount.dto.request.CreateBankAccountParam;
import com.example.minibank.bankaccount.dto.response.BankAccountResponse;
import com.example.minibank.commons.stereotype.Interactor;
import com.example.minibank.domain.SourceAccountGateway;
import com.example.minibank.domain.model.SourceAccount;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Interactor
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CreateBankAccountImpl implements CreateBankAccount {

    private final SourceAccountGateway sourceAccountGateway;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public BankAccountResponse execute(String referenceNo, CreateBankAccountParam createBankAccountParam) {
        SourceAccount sourceAccount = new SourceAccount();
        sourceAccount.setUserReferenceNo(referenceNo);
        sourceAccount.setAmount(createBankAccountParam.getInitialDeposit());
        sourceAccount.setCurrency(createBankAccountParam.getCurrency());
        sourceAccount.setType(createBankAccountParam.getType());
        return mapper.map(sourceAccountGateway.saveSourceAccount(sourceAccount), BankAccountResponse.class);
    }
}
