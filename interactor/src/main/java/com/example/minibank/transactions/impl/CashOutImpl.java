package com.example.minibank.transactions.impl;

import com.example.minibank.commons.exception.NotFoundException;
import com.example.minibank.commons.exception.NotValidException;
import com.example.minibank.commons.stereotype.Interactor;
import com.example.minibank.domain.SourceAccountGateway;
import com.example.minibank.domain.TransactionGateway;
import com.example.minibank.domain.model.SourceAccount;
import com.example.minibank.domain.model.Transaction;
import com.example.minibank.transactions.CashOut;
import com.example.minibank.transactions.dto.request.CashOutParam;
import com.example.minibank.transactions.dto.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static com.example.minibank.commons.constant.TransactionStatus.SUCCESS;
import static com.example.minibank.commons.constant.TransactionType.WITHDRAWAL;

@Transactional
@Interactor
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CashOutImpl implements CashOut {

    private final ModelMapper mapper = new ModelMapper();

    private final SourceAccountGateway sourceAccountGateway;
    private final TransactionGateway transactionGateway;

    @Override
    public TransactionResponse execute(String accountNumber, CashOutParam cashOutParam) {

        final Optional<SourceAccount> sourceAccountOptional = sourceAccountGateway
                .getSourceAccountByAccountNumber(accountNumber);

        if (!sourceAccountOptional.isPresent()) {
            throw new NotFoundException("No records found for [" + accountNumber + "].");
        }

        SourceAccount sourceAccount = sourceAccountOptional.get();

        final double currentBalance = sourceAccount.getAmount().doubleValue();
        final double amount = cashOutParam.getAmount().doubleValue();

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(new BigDecimal(amount).setScale(4, RoundingMode.HALF_UP));
        transaction.setType(WITHDRAWAL);
        transaction.setTransactionStatus(SUCCESS);

        if (amount > currentBalance) {
            transaction.setCurrency(sourceAccount.getCurrency());
            transaction.setRemainingBalance(sourceAccount.getAmount().setScale(4, RoundingMode.HALF_UP));
            transaction.setFreeText("Insufficient balance [" + currentBalance + "]");
            transactionGateway.saveTransaction(transaction);
            throw new NotValidException("Insufficient balance [" + currentBalance + "].");
        }

        double total = currentBalance - amount;

        sourceAccount.setAmount(new BigDecimal(total));

        final SourceAccount updatedSourceAccount = sourceAccountGateway.saveSourceAccount(sourceAccount);

        transaction.setCurrency(updatedSourceAccount.getCurrency());
        transaction.setRemainingBalance(updatedSourceAccount.getAmount().setScale(4, RoundingMode.HALF_UP));
        transaction.setFreeText("Withdrawal successful. "
                + "Your new balance " + updatedSourceAccount.getAmount().setScale(4, RoundingMode.HALF_UP));
        transactionGateway.saveTransaction(transaction);

        return mapper.map(transactionGateway.saveTransaction(transaction), TransactionResponse.class);
    }
}
