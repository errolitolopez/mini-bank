package com.example.minibank.transactions.impl;

import com.example.minibank.commons.exception.NotFoundException;
import com.example.minibank.commons.exception.NotValidException;
import com.example.minibank.commons.stereotype.Interactor;
import com.example.minibank.domain.SourceAccountGateway;
import com.example.minibank.domain.TransactionGateway;
import com.example.minibank.domain.model.SourceAccount;
import com.example.minibank.domain.model.Transaction;
import com.example.minibank.transactions.CloseAccount;
import com.example.minibank.transactions.dto.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static com.example.minibank.commons.constant.GenericStatus.CLOSED;
import static com.example.minibank.commons.constant.TransactionStatus.SUCCESS;
import static com.example.minibank.commons.constant.TransactionType.CLOSED_ACCOUNT;

@Interactor
@RequiredArgsConstructor
public class CloseAccountImpl implements CloseAccount {

    private final ModelMapper mapper = new ModelMapper();
    private final SourceAccountGateway sourceAccountGateway;
    private final TransactionGateway transactionGateway;

    @Override
    public TransactionResponse execute(String accountNumber) {
        final Optional<SourceAccount> sourceAccountOptional = sourceAccountGateway
                .getSourceAccountByAccountNumber(accountNumber);

        if (!sourceAccountOptional.isPresent()) {
            throw new NotFoundException("No records found for [" + accountNumber + "].");
        }

        if (sourceAccountOptional.get().getStatus().equals(CLOSED)) {
            throw new NotValidException("Account [" + accountNumber + "] already closed.");
        }

        sourceAccountOptional.get().setStatus(CLOSED);

        final SourceAccount updatedSourceAccount = sourceAccountGateway.saveSourceAccount(sourceAccountOptional.get());
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(new BigDecimal(0));
        transaction.setType(CLOSED_ACCOUNT);
        transaction.setCurrency(updatedSourceAccount.getCurrency());
        transaction.setRemainingBalance(updatedSourceAccount.getAmount());
        transaction.setTransactionStatus(SUCCESS);
        transaction.setFreeText("Account successfully closed.");
        return mapper.map(transactionGateway.saveTransaction(transaction), TransactionResponse.class);
    }
}
