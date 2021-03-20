package com.example.minibank.data.gateway;

import com.example.minibank.data.persistence.entity.SourceAccountEntity;
import com.example.minibank.data.persistence.repository.SourceAccountRepository;
import com.example.minibank.data.stereotype.Gateway;
import com.example.minibank.domain.SourceAccountGateway;
import com.example.minibank.domain.model.SourceAccount;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Gateway
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SourceAccountGatewayImpl implements SourceAccountGateway {

    private final SourceAccountRepository sourceAccountRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public Set<SourceAccount> getAllSourceAccountsByReferenceNo(String referenceNo) {
        return sourceAccountRepository.findAllByUserReferenceNo(referenceNo)
                .stream()
                .map(o -> mapper.map(o, SourceAccount.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<SourceAccount> getSourceAccountByAccountNumber(String accountNumber) {
        return sourceAccountRepository.findFirstByAccountNumberOrderByCreatedDateDesc(accountNumber)
                .map(o -> mapper.map(o, SourceAccount.class));
    }

    @Override
    public SourceAccount saveSourceAccount(SourceAccount sourceAccount) {
        final SourceAccountEntity sourceAccountEntity = mapper.map(sourceAccount, SourceAccountEntity.class);
        return mapper.map(sourceAccountRepository.save(sourceAccountEntity), SourceAccount.class);
    }
}
