package com.example.minibank.transactions.impl;

import com.example.minibank.commons.stereotype.Interactor;
import com.example.minibank.domain.TransactionGateway;
import com.example.minibank.transactions.GetAllTransactionsByAccountNumber;
import com.example.minibank.transactions.dto.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Interactor
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GetAllTransactionsByAccountNumberImpl implements GetAllTransactionsByAccountNumber {

    private final TransactionGateway transactionGateway;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<TransactionResponse> execute(String accountNumber) {
        return transactionGateway.getAllTransactionHistoriesByAccountNumber(accountNumber)
                .stream()
                .map(o -> mapper.map(o, TransactionResponse.class))
                .collect(Collectors.toList());
    }
}
