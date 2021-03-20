package com.example.minibank.account.impl;

import com.example.minibank.account.GetAccountByAccountNumber;
import com.example.minibank.account.GetAccountByReferenceNo;
import com.example.minibank.account.dto.response.AccountResponse;
import com.example.minibank.bankaccount.GetAllBalances;
import com.example.minibank.bankaccount.dto.response.BankAccountResponse;
import com.example.minibank.commons.exception.NotFoundException;
import com.example.minibank.commons.stereotype.Interactor;
import com.example.minibank.domain.SourceAccountGateway;
import com.example.minibank.domain.model.SourceAccount;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;

@Interactor
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GetAccountByAccountNumberImpl implements GetAccountByAccountNumber {

    private final SourceAccountGateway sourceAccountGateway;
    private final ModelMapper mapper = new ModelMapper();

    private final GetAccountByReferenceNo getAccountByReferenceNo;
    private final GetAllBalances getAllBalances;

    @Override
    public Optional<AccountResponse> execute(String accountNumber) {
        final Optional<String> referenceNumberOptional = sourceAccountGateway
                .getSourceAccountByAccountNumber(accountNumber)
                .map(SourceAccount::getUserReferenceNo);

        if (referenceNumberOptional.isPresent()) {
            try {

                final AccountResponse accountResponse = getAccountByReferenceNo
                        .execute(referenceNumberOptional.get());

                final Set<BankAccountResponse> balances = getAllBalances.execute(accountResponse.getReferenceNo());
                accountResponse.setBalances(balances);

                return Optional.of(accountResponse);

            } catch (NotFoundException ignored) {
            }
        }

        return Optional.empty();
    }
}
