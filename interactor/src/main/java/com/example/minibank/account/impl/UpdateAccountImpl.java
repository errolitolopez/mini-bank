package com.example.minibank.account.impl;

import com.example.minibank.account.UpdateAccount;
import com.example.minibank.account.dto.request.UpdateAccountParam;
import com.example.minibank.account.dto.response.AccountResponse;
import com.example.minibank.bankaccount.GetAllBalances;
import com.example.minibank.bankaccount.dto.response.BankAccountResponse;
import com.example.minibank.commons.constant.RegEx;
import com.example.minibank.commons.exception.NotValidException;
import com.example.minibank.commons.stereotype.Interactor;
import com.example.minibank.domain.UserGateway;
import com.example.minibank.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;

@Interactor
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UpdateAccountImpl implements UpdateAccount {

    private final ModelMapper mapper = new ModelMapper();

    private final UserGateway userGateway;
    private final GetAllBalances getAllBalances;

    @Override
    public AccountResponse execute(long id, UpdateAccountParam updateAccountParam) {

        final Optional<User> userByIdOptional = userGateway.getUserById(id);

        if (!userByIdOptional.isPresent()) {
            throw new NotValidException("Invalid account with id [" + id + "].");
        }

        final String email = updateAccountParam.getEmail();

        User user = userByIdOptional.get();

        if (!StringUtils.isEmpty(email)) {

            if (!email.matches(RegEx.EMAIL.getPattern())) {
                throw new NotValidException("Invalid E-mail address format [" + email + "].");
            }

            if (!userByIdOptional.get().getEmail().equalsIgnoreCase(email)) {

                final Optional<User> userByEmailOptional = userGateway.getUserByEmail(email);

                if (userByEmailOptional.isPresent()) {
                    throw new NotValidException("E-mail address [" + email + "] already exists.");
                }

                user.setEmail(email);
            }
        }

        final String name = updateAccountParam.getName();
        if (!StringUtils.isEmpty(name)) {

            if (name.length() >= 256) {
                throw new NotValidException("Name length is out of range [" + name + "].");
            }

            user.setName(name);
        }

        final User updatedUser = userGateway.saveUser(user);

        final AccountResponse accountResponse = mapper.map(updatedUser, AccountResponse.class);

        final Set<BankAccountResponse> balances = getAllBalances.execute(accountResponse.getReferenceNo());
        accountResponse.setBalances(balances);

        return accountResponse;
    }
}
