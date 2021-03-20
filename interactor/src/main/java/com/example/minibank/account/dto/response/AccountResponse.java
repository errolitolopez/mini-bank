package com.example.minibank.account.dto.response;

import com.example.minibank.bankaccount.dto.response.BankAccountResponse;
import com.example.minibank.commons.model.UserBase;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AccountResponse extends UserBase {

    Set<BankAccountResponse> balances = new HashSet<>();
}
