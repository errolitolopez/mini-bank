package com.example.minibank.data.gateway;

import com.example.minibank.data.persistence.entity.TransactionEntity;
import com.example.minibank.data.persistence.repository.TransactionRepository;
import com.example.minibank.data.stereotype.Gateway;
import com.example.minibank.domain.TransactionGateway;
import com.example.minibank.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Gateway
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TransactionGatewayImpl implements TransactionGateway {

    private final ModelMapper mapper = new ModelMapper();
    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactionHistoriesByAccountNumber(String accountNumber) {
        return transactionRepository.findAllByAccountNumberOrderByDateDesc(accountNumber)
                .stream()
                .map(o -> mapper.map(o, Transaction.class))
                .collect(Collectors.toList());
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        final TransactionEntity transactionEntity = mapper.map(transaction, TransactionEntity.class);
        return mapper.map(transactionRepository.save(transactionEntity), Transaction.class);
    }
}
