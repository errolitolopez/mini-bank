package com.example.minibank.account.impl;

import com.example.minibank.account.GetAllAccountsBySearchKeyPaginated;
import com.example.minibank.account.dto.response.AccountResponse;
import com.example.minibank.bankaccount.GetAllBalances;
import com.example.minibank.bankaccount.dto.response.BankAccountResponse;
import com.example.minibank.commons.dto.response.PageDTO;
import com.example.minibank.commons.exception.NotFoundException;
import com.example.minibank.commons.stereotype.Interactor;
import com.example.minibank.domain.UserGateway;
import com.example.minibank.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Interactor
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GetAllAccountsBySearchKeyPaginatedImpl implements GetAllAccountsBySearchKeyPaginated {

    private final UserGateway userGateway;
    private final GetAllBalances getAllBalances;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public PageDTO<AccountResponse> execute(String searchKey, PageRequest pageRequest) {

        final Page<User> userPage = userGateway
                .getAllUsersBySearchKeyPaginated(searchKey, pageRequest);

        if (userPage.getTotalElements() == 0) {
            throw new NotFoundException("No records found for [" + searchKey + "].");
        }

        final List<AccountResponse> results = userPage.getContent()
                .stream()
                .map(o -> {
                    final AccountResponse accountResponse = mapper.map(o, AccountResponse.class);

                    final Set<BankAccountResponse> bankAccountRespons = getAllBalances
                            .execute(accountResponse.getReferenceNo());

                    accountResponse.setBalances(bankAccountRespons);

                    return accountResponse;
                })
                .collect(Collectors.toList());

        final PageDTO<AccountResponse> pageDTO = new PageDTO<>();
        pageDTO.setData(results);

        final long totalElements = userPage.getTotalElements();
        pageDTO.setTotalElements(totalElements);

        pageDTO.setCurrentPage(userPage.getNumber());
        pageDTO.setTotalPages(userPage.getTotalPages());
        return pageDTO;
    }
}
