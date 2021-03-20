package com.example.minibank.app.service.impl;

import com.example.minibank.account.GetAccountByAccountNumber;
import com.example.minibank.account.dto.response.AccountResponse;
import com.example.minibank.app.dto.request.CashInForm;
import com.example.minibank.app.dto.request.CashOutForm;
import com.example.minibank.app.dto.response.BankTransactionResource;
import com.example.minibank.app.service.BankTransactionService;
import com.example.minibank.bankaccount.dto.response.BankAccountResponse;
import com.example.minibank.commons.dto.response.ServiceResponse;
import com.example.minibank.commons.exception.NotFoundException;
import com.example.minibank.exchangerate.GetAllExchangeRates;
import com.example.minibank.exchangerate.dto.response.ExchangeRateResponse;
import com.example.minibank.transactions.CashIn;
import com.example.minibank.transactions.CashOut;
import com.example.minibank.transactions.CloseAccount;
import com.example.minibank.transactions.GetAllTransactionsByAccountNumber;
import com.example.minibank.transactions.dto.request.CashInParam;
import com.example.minibank.transactions.dto.request.CashOutParam;
import com.example.minibank.transactions.dto.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BankTransactionServiceImpl implements BankTransactionService {

    private final ModelMapper mapper = new ModelMapper();
    private final GetAllTransactionsByAccountNumber getAllTransactionsByAccountNumber;
    private final GetAccountByAccountNumber getAccountByAccountNumber;
    private final GetAllExchangeRates getAllExchangeRates;
    private final CashIn cashIn;
    private final CashOut cashOut;
    private final CloseAccount closeAccount;

    @Override
    public ServiceResponse<List<BankTransactionResource>> getAllBankingTransactionsByAccountNumber(String accountNumber) {
        final Optional<String> nameOptional = getAccountByAccountNumber
                .execute(accountNumber).map(AccountResponse::getName);

        final List<BankTransactionResource> bankTransactionResources = getAllTransactionsByAccountNumber
                .execute(accountNumber)
                .stream()
                .map(o -> {
                    BankTransactionResource bankTransactionResource = mapper
                            .map(o, BankTransactionResource.class);

                    nameOptional.ifPresent(bankTransactionResource::setName);

                    return bankTransactionResource;
                })
                .limit(10) // latest 10
                .collect(Collectors.toList());

        if (!bankTransactionResources.isEmpty()) {
            return new ServiceResponse<>(bankTransactionResources);
        }
        throw new NotFoundException("No records found for [" + accountNumber + "].");
    }

    @Override
    public ServiceResponse<BankTransactionResource> cashIn(String accountNumber, CashInForm cashInForm) {
        final Optional<AccountResponse> accountResponseOptional = getAccountByAccountNumber
                .execute(accountNumber);

        if (!accountResponseOptional.isPresent()) {
            throw new NotFoundException("No records found for [" + accountNumber + "].");
        }

        final Optional<BankAccountResponse> bankAccountResponseOptional = accountResponseOptional.get().getBalances()
                .stream()
                .filter(o -> o.getAccountNumber().equalsIgnoreCase(accountNumber))
                .findFirst();

        if (!bankAccountResponseOptional.isPresent()) {
            throw new NotFoundException("No records found for [" + accountNumber + "].");
        }

        final String currency = cashInForm.getCurrency();

        final List<ExchangeRateResponse> exchangeRates = getAllExchangeRates.execute();

        final Optional<ExchangeRateResponse> exchangeRateResponseOptional = exchangeRates
                .stream()
                .filter(o -> o.getCurrency().equalsIgnoreCase(currency))
                .findFirst();

        if (!exchangeRateResponseOptional.isPresent()) {
            throw new NotFoundException("Invalid currency [" + currency + "].");
        }

        final String accountCurrency = bankAccountResponseOptional.get().getCurrency();

        final Optional<BigDecimal> accountCurrencyValueOptional = exchangeRates
                .stream()
                .filter(o -> o.getCurrency().equalsIgnoreCase(accountCurrency))
                .map(ExchangeRateResponse::getValue)
                .findFirst();

        if (!accountCurrencyValueOptional.isPresent()) {
            throw new NotFoundException("Account currency [" + accountCurrency + "] does not exists. " +
                    "Please try again later.");
        }

        BigDecimal cashInAmount = cashInForm.getAmount();

        final boolean isConverted = !currency.equalsIgnoreCase(accountCurrency);
        CashInParam cashInParam = new CashInParam();
        cashInParam.setAmount(cashInAmount);
        cashInParam.setConverted(isConverted);
        cashInParam.setCurrency(currency);

        // Conversion
        if (isConverted) {
            final BigDecimal cashInBaseCurrencyValue = exchangeRateResponseOptional.get().getValue();

            double valueInPeso = 1 / cashInBaseCurrencyValue.doubleValue();

            double valueInPesoMultipliedByCashInAmount = valueInPeso * cashInAmount.doubleValue();

            double total = valueInPesoMultipliedByCashInAmount * accountCurrencyValueOptional.get().doubleValue();

            cashInParam.setTotal(new BigDecimal(total));
        }

        final TransactionResponse transactionResponse = cashIn.execute(accountNumber, cashInParam);

        final BankTransactionResource bankTransactionResource = mapper
                .map(transactionResponse, BankTransactionResource.class);

        bankTransactionResource.setName(accountResponseOptional.get().getName());

        return new ServiceResponse<>(bankTransactionResource);
    }

    @Override
    public ServiceResponse<BankTransactionResource> cashOut(String accountNumber, CashOutForm cashOutForm) {
        final Optional<AccountResponse> accountResponseOptional = getAccountByAccountNumber
                .execute(accountNumber);

        if (!accountResponseOptional.isPresent()) {
            throw new NotFoundException("No records found for [" + accountNumber + "].");
        }

        final Optional<BankAccountResponse> bankAccountResponseOptional = accountResponseOptional.get().getBalances()
                .stream()
                .filter(o -> o.getAccountNumber().equalsIgnoreCase(accountNumber))
                .findFirst();

        if (!bankAccountResponseOptional.isPresent()) {
            throw new NotFoundException("No records found for [" + accountNumber + "].");
        }

        final TransactionResponse transactionResponse = cashOut
                .execute(accountNumber, new CashOutParam(cashOutForm.getAmount()));

        final BankTransactionResource bankTransactionResource = mapper
                .map(transactionResponse, BankTransactionResource.class);

        bankTransactionResource.setName(accountResponseOptional.get().getName());

        return new ServiceResponse<>(bankTransactionResource);
    }

    @Override
    public ServiceResponse<BankTransactionResource> closeAccount(String accountNumber) {
        final Optional<AccountResponse> accountResponseOptional = getAccountByAccountNumber
                .execute(accountNumber);

        if (!accountResponseOptional.isPresent()) {
            throw new NotFoundException("No records found for [" + accountNumber + "].");
        }

        final TransactionResponse transactionResponse = closeAccount.execute(accountNumber);

        final BankTransactionResource bankTransactionResource = mapper
                .map(transactionResponse, BankTransactionResource.class);

        bankTransactionResource.setName(accountResponseOptional.get().getName());

        return new ServiceResponse<>(bankTransactionResource);
    }
}
