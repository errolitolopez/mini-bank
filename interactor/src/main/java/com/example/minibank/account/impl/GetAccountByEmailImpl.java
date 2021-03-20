package com.example.minibank.account.impl;

import com.example.minibank.account.GetAccountByEmail;
import com.example.minibank.account.dto.response.AccountResponse;
import com.example.minibank.bankaccount.GetAllBalances;
import com.example.minibank.bankaccount.dto.response.BankAccountResponse;
import com.example.minibank.commons.exception.NotFoundException;
import com.example.minibank.commons.stereotype.Interactor;
import com.example.minibank.domain.UserGateway;
import com.example.minibank.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;

@Interactor
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GetAccountByEmailImpl implements GetAccountByEmail {

    private final ModelMapper mapper = new ModelMapper();

    private final UserGateway userGateway;
    private final GetAllBalances getAllBalances;

    @Override
    public AccountResponse execute(String email) {
        final Optional<User> userByIdOptional = userGateway.getUserByEmail(email);

        if (!userByIdOptional.isPresent()) {
            throw new NotFoundException("No records found for [" + email + "].");
        }

        final AccountResponse accountResponse = mapper.map(userByIdOptional.get(), AccountResponse.class);

        final Set<BankAccountResponse> balances = getAllBalances.execute(accountResponse.getReferenceNo());
        accountResponse.setBalances(balances);

        return accountResponse;
    }
}
