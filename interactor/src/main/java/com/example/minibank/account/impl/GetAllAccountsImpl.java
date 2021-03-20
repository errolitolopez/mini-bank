package com.example.minibank.account.impl;

import com.example.minibank.account.GetAllAccounts;
import com.example.minibank.account.dto.response.AccountResponse;
import com.example.minibank.bankaccount.GetAllBalances;
import com.example.minibank.bankaccount.dto.response.BankAccountResponse;
import com.example.minibank.commons.stereotype.Interactor;
import com.example.minibank.domain.UserGateway;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Interactor
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GetAllAccountsImpl implements GetAllAccounts {

    private final ModelMapper mapper = new ModelMapper();

    private final UserGateway userGateway;
    private final GetAllBalances getAllBalances;

    @Override
    public List<AccountResponse> execute() {
        return userGateway.getAllUsers()
                .stream()
                .map(o -> {
                    final AccountResponse accountResponse = mapper.map(o, AccountResponse.class);

                    final Set<BankAccountResponse> bankAccountRespons = getAllBalances
                            .execute(accountResponse.getReferenceNo());

                    accountResponse.setBalances(bankAccountRespons);

                    return accountResponse;
                })
                .collect(Collectors.toList());
    }
}
