package com.example.minibank.account.impl;

import com.example.minibank.account.DeleteAccountById;
import com.example.minibank.account.GetAccountById;
import com.example.minibank.account.dto.response.AccountResponse;
import com.example.minibank.bankaccount.GetAllBalances;
import com.example.minibank.bankaccount.dto.response.BankAccountResponse;
import com.example.minibank.commons.stereotype.Interactor;
import com.example.minibank.domain.UserGateway;
import com.example.minibank.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static com.example.minibank.commons.constant.GenericStatus.CLOSED;

@Interactor
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DeleteAccountByIdImpl implements DeleteAccountById {

    private final ModelMapper mapper = new ModelMapper();

    private final UserGateway userGateway;
    private final GetAccountById getAccountById;
    private final GetAllBalances getAllBalances;

    @Override
    public AccountResponse execute(long id) {
        User user = mapper.map(getAccountById.execute(id), User.class);
        user.setStatus(CLOSED);

        final User updatedUser = userGateway.saveUser(user);

        final AccountResponse accountResponse = mapper.map(updatedUser, AccountResponse.class);

        final Set<BankAccountResponse> balances = getAllBalances.execute(accountResponse.getReferenceNo());
        accountResponse.setBalances(balances);

        return accountResponse;
    }
}
