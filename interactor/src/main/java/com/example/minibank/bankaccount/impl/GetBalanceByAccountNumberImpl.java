package com.example.minibank.bankaccount.impl;

import com.example.minibank.bankaccount.GetBalanceByAccountNumber;
import com.example.minibank.bankaccount.dto.response.BankAccountResponse;
import com.example.minibank.commons.stereotype.Interactor;
import com.example.minibank.domain.SourceAccountGateway;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Interactor
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GetBalanceByAccountNumberImpl implements GetBalanceByAccountNumber {

    private final ModelMapper mapper = new ModelMapper();
    private final SourceAccountGateway sourceAccountGateway;

    @Override
    public Optional<BankAccountResponse> execute(String accountNumber) {
        return sourceAccountGateway.getSourceAccountByAccountNumber(accountNumber)
                .map(o -> mapper.map(o, BankAccountResponse.class));
    }
}
