package com.example.minibank.domain;

import com.example.minibank.domain.model.SourceAccount;

import java.util.Optional;
import java.util.Set;

public interface SourceAccountGateway {
    Set<SourceAccount> getAllSourceAccountsByReferenceNo(String referenceNo);

    Optional<SourceAccount> getSourceAccountByAccountNumber(String accountNumber);

    SourceAccount saveSourceAccount(SourceAccount sourceAccount);
}
