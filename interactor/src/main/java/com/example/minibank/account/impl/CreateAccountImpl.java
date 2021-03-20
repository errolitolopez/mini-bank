package com.example.minibank.account.impl;

import com.example.minibank.account.CreateAccount;
import com.example.minibank.account.dto.request.CreateAccountParam;
import com.example.minibank.account.dto.response.AccountResponse;
import com.example.minibank.commons.exception.NotValidException;
import com.example.minibank.commons.stereotype.Interactor;
import com.example.minibank.domain.UserGateway;
import com.example.minibank.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Interactor
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CreateAccountImpl implements CreateAccount {

    private final UserGateway userGateway;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public AccountResponse execute(CreateAccountParam createAccountParam) {

        final Optional<User> userOptional = userGateway.getUserByEmail(createAccountParam.getEmail());

        if (userOptional.isPresent()) {
            throw new NotValidException("E-mail address [" + userOptional.get().getEmail() + "] already exists.");
        }

        final User createdUser = userGateway.saveUser(mapper.map(createAccountParam, User.class));

        return mapper.map(createdUser, AccountResponse.class);
    }
}
