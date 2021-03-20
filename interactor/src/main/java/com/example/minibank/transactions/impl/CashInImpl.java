package com.example.minibank.transactions.impl;

import com.example.minibank.commons.exception.NotFoundException;
import com.example.minibank.commons.stereotype.Interactor;
import com.example.minibank.domain.SourceAccountGateway;
import com.example.minibank.domain.TransactionGateway;
import com.example.minibank.domain.model.SourceAccount;
import com.example.minibank.domain.model.Transaction;
import com.example.minibank.transactions.CashIn;
import com.example.minibank.transactions.dto.request.CashInParam;
import com.example.minibank.transactions.dto.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static com.example.minibank.commons.constant.TransactionStatus.SUCCESS;
import static com.example.minibank.commons.constant.TransactionType.DEPOSIT;
import static com.example.minibank.commons.constant.TransactionType.DEPOSIT_WITH_CONVERSION;

@Transactional
@Interactor
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CashInImpl implements CashIn {

    private final ModelMapper mapper = new ModelMapper();

    private final SourceAccountGateway sourceAccountGateway;
    private final TransactionGateway transactionGateway;

    @Override
    public TransactionResponse execute(String accountNumber, CashInParam cashInParam) {

        final Optional<SourceAccount> sourceAccountOptional = sourceAccountGateway
                .getSourceAccountByAccountNumber(accountNumber);

        if (!sourceAccountOptional.isPresent()) {
            throw new NotFoundException("No records found for [" + accountNumber + "].");
        }

        final boolean isConverted = cashInParam.isConverted();
        final BigDecimal amount = cashInParam.getAmount();
        final BigDecimal total = cashInParam.getTotal();
        final String currency = cashInParam.getCurrency();

        SourceAccount sourceAccount = sourceAccountOptional.get();

        double balance = sourceAccount.getAmount().doubleValue();

        if (isConverted) {
            balance = balance + total.doubleValue();

        } else {
            balance = balance + amount.doubleValue();
        }

        sourceAccount.setAmount(new BigDecimal(balance));

        final SourceAccount updatedSourceAccount = sourceAccountGateway.saveSourceAccount(sourceAccount);

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setCurrency(currency);
        transaction.setAmount(amount);
        transaction.setRemainingBalance(updatedSourceAccount.getAmount().setScale(4, RoundingMode.HALF_UP));
        transaction.setType(isConverted ? DEPOSIT_WITH_CONVERSION : DEPOSIT);
        transaction.setTransactionStatus(SUCCESS);

        transaction.setFreeText("DEPOSITED " + currency + " " + amount + (isConverted ? " AND CONVERTED TO "
                + updatedSourceAccount.getCurrency() + " " + (total.setScale(4, RoundingMode.HALF_UP)) : ""));

        return mapper.map(transactionGateway.saveTransaction(transaction), TransactionResponse.class);
    }
}
