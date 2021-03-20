package com.example.minibank.bankaccount.impl;

import com.example.minibank.bankaccount.GetAllBalances;
import com.example.minibank.bankaccount.dto.response.BankAccountResponse;
import com.example.minibank.commons.stereotype.Interactor;
import com.example.minibank.domain.SourceAccountGateway;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.minibank.commons.constant.GenericStatus.CLOSED;

@Interactor
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GetAllBalancesImpl implements GetAllBalances {

    private final SourceAccountGateway sourceAccountGateway;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public Set<BankAccountResponse> execute(String referenceNo) {

        return sourceAccountGateway.getAllSourceAccountsByReferenceNo(referenceNo)
                .stream()
                .filter(o -> !o.getStatus().equals(CLOSED))
                .map(o -> mapper.map(o, BankAccountResponse.class))
                .collect(Collectors.toSet());
    }
}
