package com.example.minibank.account.mapper;

import com.example.minibank.account.dto.response.AccountResponse;
import com.example.minibank.commons.dto.response.PageDTO;
import com.example.minibank.commons.stereotype.Mapper;
import com.example.minibank.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AccountPageDTOMapper {

    private final ModelMapper mapper = new ModelMapper();

    public PageDTO<AccountResponse> map(PageDTO<User> page) {

        final List<AccountResponse> results = page.getData()
                .stream()
                .map(o -> mapper.map(o, AccountResponse.class))
                .collect(Collectors.toList());

        final PageDTO<AccountResponse> pageDTO = new PageDTO<>();
        pageDTO.setData(results);

        final long totalElements = page.getTotalElements();
        pageDTO.setTotalElements(totalElements);

        pageDTO.setCurrentPage(totalElements == 0 ? 0 : page.getCurrentPage() + 1);
        pageDTO.setTotalPages(page.getTotalPages());
        return pageDTO;
    }
}
